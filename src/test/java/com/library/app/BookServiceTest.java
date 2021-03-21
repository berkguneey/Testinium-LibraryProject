package com.library.app;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.library.app.dto.BookDTO;
import com.library.app.dto.StoreBookDTO;
import com.library.app.exception.DataConflictException;
import com.library.app.exception.NotFoundException;
import com.library.app.exception.ServiceException;
import com.library.app.mapper.BaseMapper;
import com.library.app.model.Book;
import com.library.app.model.Category;
import com.library.app.model.Store;
import com.library.app.model.StoreBook;
import com.library.app.repository.BookRepository;
import com.library.app.repository.CategoryRepository;
import com.library.app.repository.StoreBookRepository;
import com.library.app.repository.StoreRepository;
import com.library.app.service.impl.BookServiceImpl;
import com.library.app.service.impl.CategoryServiceImpl;
import com.library.app.service.impl.StoreServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {
	
	@Mock
	private CategoryRepository categoryRepository;
	
	@Mock
	private BookRepository bookRepository;
	
	@Mock
	private StoreRepository storeRepository;
	
	@Mock
	private StoreBookRepository storeBookRepository;
	
	@InjectMocks
	private BookServiceImpl bookServiceImpl;
	
	@InjectMocks
	private CategoryServiceImpl categoryServiceImpl;
	
	@InjectMocks
	private StoreServiceImpl storeServiceImpl;
	
	private Category category1;
	private Category category2;
	
	private Book book1;
	private Book book2;
	private Book book3;
	private Book book4;
	
	private Store store1;
	
	private StoreBook storeBook1;
	private StoreBook storeBook2;

	private final List<Book> books = new ArrayList<>();
	private final List<StoreBook> storeBooks = new ArrayList<>();
	
	@Before
	public void setUp() {
		
		category1 = Category.builder().id(1L).name("Aksiyon").build();
		category2 = Category.builder().id(2L).name("Polisiye").build();
		
		book1 = Book.builder().id(1L).name("A Kitap").category(category1).build();
		book2 = Book.builder().id(2L).name("B Kitap").category(category1).build();
		book3 = Book.builder().id(3L).name("C Kitap").category(category1).build();
		book4 = Book.builder().id(4L).name("D Kitap").category(category2).build();
		books.add(book1);
		books.add(book2);
		books.add(book3);
		books.add(book4);
		
		store1 = Store.builder().id(1L).name("A Kütüphanesi").city("İstanbul").build();
		
		storeBook1 = StoreBook.builder().id(1L).book(book1).store(store1).price(new BigDecimal(20)).build();
		storeBook2 = StoreBook.builder().id(2L).book(book2).store(store1).price(new BigDecimal(30)).build();
		storeBooks.add(storeBook1);
		storeBooks.add(storeBook2);
	}

	@Test
	public void shouldReturnAllBooksSuccessfully() throws InterruptedException, ServiceException {

		when(bookRepository.findAll()).thenReturn(books);

		List<BookDTO> bookDTOList = bookServiceImpl.findBooks();

		assertNotNull(bookDTOList);

		assertEquals(books.size(), bookDTOList.size());

	}
	
	@Test
	public void shouldReturnFindingBookSuccessfully() throws InterruptedException, ServiceException {

		when(bookRepository.findById(2L)).thenReturn(Optional.of(book2));

		BookDTO bookDTO = bookServiceImpl.findBook(2L);

		assertNotNull(bookDTO);

		assertEquals(book2.getName(), bookDTO.getName());

	}
	
	@Test(expected = NotFoundException.class)
	public void shouldReturnFindingCategoryWithNotFoundExceptionSuccessfully() throws InterruptedException, ServiceException {

		when(bookRepository.findById(5L)).thenReturn(Optional.empty());

		BookDTO bookDTO = bookServiceImpl.findBook(5L);
		
		assertThat(bookDTO).isNotNull();

	}
	
	@Test
	public void shouldSavedBookSuccessfully() throws InterruptedException, ServiceException {
		
		final Book book = Book.builder().name("E Kitap").category(category1).build();
		
		when(bookRepository.save(book)).then(i -> i.getArgument(0));
		
		when(bookRepository.existsByNameLike(book.getName())).thenReturn(false);
		
		when(categoryRepository.findById(book.getCategory().getId())).thenReturn(Optional.of(category1));
		
		BookDTO bookDTO = bookServiceImpl.createBook(BaseMapper.map(book, BookDTO.class));

		assertThat(bookDTO).isNotNull();
		
		assertEquals(book.getName(), bookDTO.getName());

	}
	
	@Test(expected = DataConflictException.class)
	public void shouldSavedBookWithDataConflictException() throws InterruptedException, ServiceException {
		
		final Book book = Book.builder().name("E Kitap").category(category1).build();
		
		when(bookRepository.save(book)).then(i -> i.getArgument(0));
		
		when(bookRepository.existsByNameLike(book.getName())).thenReturn(true);
		
		when(categoryRepository.findById(book.getCategory().getId())).thenReturn(Optional.of(category1));
		
		BookDTO bookDTO = bookServiceImpl.createBook(BaseMapper.map(book, BookDTO.class));

		assertThat(bookDTO).isNotNull();
		
		assertEquals(book.getName(), bookDTO.getName());

	}
	
	@Test
	public void shouldUpdatedBookCategorySuccessfully() throws InterruptedException, ServiceException {
		
		when(bookRepository.findById(1L)).thenReturn(Optional.of(book1));
		
		when(categoryRepository.findById(2L)).thenReturn(Optional.of(category2));
		
		when(bookRepository.save(book1)).then(i -> i.getArgument(0));
		
		book1.setCategory(category2);
		
		BookDTO bookDTO = bookServiceImpl.updateBook(book1.getId(), BaseMapper.map(book1, BookDTO.class));

		assertThat(bookDTO).isNotNull();
		
		assertEquals(book1.getCategory().getId(), bookDTO.getCategoryId());

	}
	
	@Test
	public void shouldReturnAllBookByCategoryIdSuccessfully() throws InterruptedException, ServiceException {
		
		when(categoryRepository.existsById(1L)).thenReturn(true);
		
		when(bookRepository.findByCategoryId(1L)).thenReturn(books.stream().filter(s -> s.getCategory().getId() == 1L).collect(Collectors.toList()));
		
		List<BookDTO> bookDTOList = categoryServiceImpl.findBooksByCategoryId(category1.getId());

		assertThat(bookDTOList).isNotNull();
		
		assertEquals(3, bookDTOList.size());

	}
	
	@Test
	public void shouldReturnAllBookByStoreIdSuccessfully() throws InterruptedException, ServiceException {
		
		when(storeRepository.existsById(1L)).thenReturn(true);
		
		when(storeBookRepository.findByStoreId(1L)).thenReturn(storeBooks.stream().filter(s -> s.getStore().getId() == 1L).collect(Collectors.toList()));
		
		List<StoreBookDTO> storeBookDTOList = storeServiceImpl.findBooksByStoreId(1L);

		assertThat(storeBookDTOList).isNotNull();
		
		assertEquals(2, storeBookDTOList.size());

	}
}
