package com.library.app;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.library.app.dto.StoreBookDTO;
import com.library.app.dto.StoreDTO;
import com.library.app.exception.NotFoundException;
import com.library.app.exception.ServiceException;
import com.library.app.mapper.BaseMapper;
import com.library.app.model.Book;
import com.library.app.model.Category;
import com.library.app.model.Store;
import com.library.app.model.StoreBook;
import com.library.app.repository.BookRepository;
import com.library.app.repository.StoreBookRepository;
import com.library.app.repository.StoreRepository;
import com.library.app.service.impl.StoreServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StoreServiceTest {

	@Mock
	private StoreRepository storeRepository;
	
	@Mock
	private BookRepository bookRepository;
	
	@Mock
	private StoreBookRepository storeBookRepository;

	@InjectMocks
	private StoreServiceImpl storeServiceImpl;

	private Store store1;
	private Store store2;
	private Store store3;
	
	private Book book1;
	
	private Category category1;
	
	private StoreBook deletedTest;

	private final List<Store> stores = new ArrayList<>();

	@Before
	public void setUp() {
		
		store1 = Store.builder().id(1L).name("A Kütüphanesi").build();
		store2 = Store.builder().id(2L).name("B Kütüphanesi").build();
		store3 = Store.builder().id(3L).name("C Kütüphanesi").build();
		stores.add(store1);
		stores.add(store2);
		stores.add(store3);
		
		book1 = Book.builder().id(1L).name("Kitap 1").category(category1).build();
		
		category1 = Category.builder().id(1L).name("Aksiyon").build();
		
		deletedTest = StoreBook.builder().id(2L).store(store2).book(book1).build();
		
	}

	@Test
	public void shouldReturnAllStoresSuccessfully() throws InterruptedException, ServiceException {

		when(storeRepository.findAll()).thenReturn(stores);

		List<StoreDTO> storeDTOList = storeServiceImpl.findStores();

		assertNotNull(storeDTOList);

		assertEquals(stores.size(), storeDTOList.size());

	}

	@Test
	public void shouldReturnFindingStoreSuccessfully() throws InterruptedException, ServiceException {

		when(storeRepository.findById(2L)).thenReturn(Optional.of(store2));

		StoreDTO storeDTO = storeServiceImpl.findStore(2L);

		assertNotNull(storeDTO);

		assertEquals(store2.getName(), storeDTO.getName());

	}
	
	@Test(expected = NotFoundException.class)
	public void shouldReturnFindingStoreWithNotFoundException() throws InterruptedException, ServiceException {

		when(storeRepository.findById(4L)).thenReturn(Optional.empty());

		StoreDTO storeDTO = storeServiceImpl.findStore(4L);

		assertNotNull(storeDTO);
		
	}

	@Test
	public void shouldSavedStoreSuccessfully() throws InterruptedException, ServiceException {

		final Store store = Store.builder().name("D Kütüphanesi").city("İstanbul").build();
		
		when(storeRepository.existsByNameAndCityLike(store.getName(),store.getCity())).thenReturn(false);

		when(storeRepository.save(store)).then(i -> i.getArgument(0));

		StoreDTO storeDTO = storeServiceImpl.createStore(BaseMapper.map(store, StoreDTO.class));

		assertThat(storeDTO).isNotNull();

		assertEquals(store.getName(), storeDTO.getName());

	}
	
	@Test
	public void shouldAddedBookToStoreSuccessfully() throws InterruptedException, ServiceException {

		final StoreBook storeBook1 = StoreBook.builder().book(book1).store(store2).price(new BigDecimal(20)).build();
		
		when(storeRepository.findById(2L)).thenReturn(Optional.of(store2));
		
		when(bookRepository.findById(1L)).thenReturn(Optional.of(book1));
		
		StoreBookDTO input = new StoreBookDTO();
		input.setStoreId(store2.getId());
		input.setBookId(book1.getId());
		input.setPrice(new BigDecimal(20));
		
		when(storeBookRepository.save(storeBook1)).then(i -> i.getArgument(0));

		StoreBookDTO storeBookDTO = storeServiceImpl.createBookToStore(BaseMapper.map(input, StoreBookDTO.class));

		assertThat(storeBookDTO).isNotNull();

		assertEquals(storeBook1.getPrice(), storeBookDTO.getPrice());
		
	}
	
	@Test
	public void shouldDeletedStoreSuccessfully() throws InterruptedException, ServiceException {

		when(storeBookRepository.findByStoreIdAndBookId(2L, 1L)).thenReturn(deletedTest);
		
		storeServiceImpl.deleteBookFromStore(2L, 1L);

		verify(storeBookRepository).delete(deletedTest);
		
	}

}
