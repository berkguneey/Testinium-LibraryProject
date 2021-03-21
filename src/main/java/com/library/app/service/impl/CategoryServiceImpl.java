package com.library.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.app.dto.BookDTO;
import com.library.app.dto.CategoryDTO;
import com.library.app.exception.DataConflictException;
import com.library.app.exception.Error;
import com.library.app.exception.NotFoundException;
import com.library.app.exception.ServiceException;
import com.library.app.mapper.BaseMapper;
import com.library.app.model.Book;
import com.library.app.model.Category;
import com.library.app.repository.BookRepository;
import com.library.app.repository.CategoryRepository;
import com.library.app.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	private BookRepository bookRepository;
	private CategoryRepository categoryRepository;

	@Autowired
	public CategoryServiceImpl(BookRepository bookRepository, CategoryRepository categoryRepository) {
		this.bookRepository = bookRepository;
		this.categoryRepository = categoryRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<CategoryDTO> findCategories() {
		List<Category> categoryList = categoryRepository.findAll();
		return BaseMapper.mapAll(categoryList, CategoryDTO.class);
	}
	
	@Override
	@Transactional(readOnly = true)
	public CategoryDTO findCategory(Long id) throws ServiceException {
		Category category = findCategoryEntityById(id);
		return BaseMapper.map(category, CategoryDTO.class);
	}

	@Override
	@Transactional(rollbackFor = ServiceException.class)
	public CategoryDTO createCategory(CategoryDTO categoryDTO) throws ServiceException {
		if (categoryRepository.existsByNameLike(categoryDTO.getName())) {
			throw new DataConflictException(Error.CATEGORY_IS_ALREADY_EXIST);
		}
		Category category = BaseMapper.map(categoryDTO, Category.class);
		categoryRepository.save(category);
		return BaseMapper.map(category, CategoryDTO.class);
	}

	@Override
	@Transactional(readOnly = true)
	public List<BookDTO> findBooksByCategoryId(Long id) throws ServiceException{
		if(!categoryRepository.existsById(id)) {
			throw new NotFoundException(Error.CATEGORY_NOT_FOUND);
		}
		List<Book> bookList = bookRepository.findByCategoryId(id);
		return BaseMapper.mapAll(bookList, BookDTO.class);
	}
	
	private Category findCategoryEntityById(Long id) throws ServiceException {
		return categoryRepository.findById(id).orElseThrow(() -> new NotFoundException(Error.CATEGORY_NOT_FOUND));
	}

}
