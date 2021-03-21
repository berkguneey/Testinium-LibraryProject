package com.library.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.app.dto.BookDTO;
import com.library.app.dto.CategoryDTO;
import com.library.app.exception.ServiceException;
import com.library.app.model.response.BaseDataResponse;
import com.library.app.service.CategoryService;

@RestController
@RequestMapping("api/categories")
public class CategoryRestController {

	private final CategoryService categoryService;

	@Autowired
	public CategoryRestController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping
	public ResponseEntity<BaseDataResponse<List<CategoryDTO>>> findCategories() {
		List<CategoryDTO> categoryDTOList = categoryService.findCategories();
		return new ResponseEntity<>(new BaseDataResponse<>(true, categoryDTOList), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BaseDataResponse<CategoryDTO>> findCategory(@PathVariable Long id) throws ServiceException {
		CategoryDTO categoryDTO = categoryService.findCategory(id);
		return new ResponseEntity<>(new BaseDataResponse<>(true, categoryDTO), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<BaseDataResponse<CategoryDTO>> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) throws ServiceException {
		CategoryDTO createdCategory = categoryService.createCategory(categoryDTO);
		return new ResponseEntity<>(new BaseDataResponse<>(true, createdCategory), HttpStatus.OK);
	}

	@GetMapping("{id}/books")
	public ResponseEntity<BaseDataResponse<List<BookDTO>>> getBooksByCategoryId(@PathVariable Long id) throws ServiceException {
		List<BookDTO> bookDTOList = categoryService.findBooksByCategoryId(id);
		return new ResponseEntity<>(new BaseDataResponse<>(true, bookDTOList), HttpStatus.OK);
	}

}
