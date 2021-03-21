package com.library.app.service;

import java.util.List;

import com.library.app.dto.BookDTO;
import com.library.app.dto.CategoryDTO;
import com.library.app.exception.ServiceException;

public interface CategoryService {

	List<CategoryDTO> findCategories();
	CategoryDTO findCategory(Long id) throws ServiceException;
	CategoryDTO createCategory(CategoryDTO categoryDTO) throws ServiceException;
	List<BookDTO> findBooksByCategoryId(Long id) throws ServiceException;
	
}
