package com.library.app;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

import com.library.app.dto.CategoryDTO;
import com.library.app.exception.NotFoundException;
import com.library.app.exception.ServiceException;
import com.library.app.mapper.BaseMapper;
import com.library.app.model.Book;
import com.library.app.model.Category;
import com.library.app.repository.CategoryRepository;
import com.library.app.service.impl.CategoryServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTest {

	@Mock
	private CategoryRepository categoryRepository;

	@InjectMocks
	private CategoryServiceImpl categoryServiceImpl;

	private Category category1;
	private Category category2;
	private Category category3;
	
	private Book book1;
	private Book book2;

	private final List<Category> categories = new ArrayList<>();
	private final List<Book> books = new ArrayList<>();

	@Before
	public void setUp() {
		
		category1 = Category.builder().id(1L).name("Aksiyon").build();
		category2 = Category.builder().id(2L).name("Polisiye").build();
		category3 = Category.builder().id(3L).name("Aşk").build();
		categories.add(category1);
		categories.add(category2);
		categories.add(category3);
		
		book1 = Book.builder().id(1L).name("A Kitap").category(category1).build();
		book2 = Book.builder().id(2L).name("B Kitap").category(category1).build();
		books.add(book1);
		books.add(book2);
	}

	@Test
	public void shouldReturnAllCategoriesSuccessfully() throws InterruptedException, ServiceException {

		when(categoryRepository.findAll()).thenReturn(categories);

		List<CategoryDTO> categoryDTOList = categoryServiceImpl.findCategories();

		assertNotNull(categoryDTOList);

		assertEquals(categories.size(), categoryDTOList.size());

	}

	@Test
	public void shouldReturnFindingCategorySuccessfully() throws InterruptedException, ServiceException {

		when(categoryRepository.findById(2L)).thenReturn(Optional.of(category2));

		CategoryDTO categoryDTO = categoryServiceImpl.findCategory(2L);

		assertNotNull(categoryDTO);

		assertEquals(category2.getName(), categoryDTO.getName());

	}
	
	@Test(expected = NotFoundException.class)
	public void shouldReturnFindingCategoryWithNotFoundException() throws InterruptedException, ServiceException {

		when(categoryRepository.findById(4L)).thenReturn(Optional.empty());

		CategoryDTO categoryDTO = categoryServiceImpl.findCategory(4L);

		assertNotNull(categoryDTO);
		
	}

	@Test
	public void shouldSavedCategorySuccessfully() throws InterruptedException, ServiceException {

		final Category category = Category.builder().name("Macera").build();
		
		when(categoryRepository.existsByNameLike(category.getName())).thenReturn(false);

		when(categoryRepository.save(category)).then(i -> i.getArgument(0));

		CategoryDTO categoryDTO = categoryServiceImpl.createCategory(BaseMapper.map(category, CategoryDTO.class));

		assertThat(categoryDTO).isNotNull();

		assertEquals(category.getName(), categoryDTO.getName());

	}

}
