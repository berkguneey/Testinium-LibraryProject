package com.library.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.app.dto.BookDTO;
import com.library.app.exception.ServiceException;
import com.library.app.model.request.BookUpdateRequest;
import com.library.app.model.response.BaseDataResponse;
import com.library.app.service.BookService;

@RestController
@RequestMapping("api/books")
public class BookRestController {

	private BookService bookService;

	@Autowired
	public BookRestController(BookService bookService) {
		this.bookService = bookService;
	}
	
	@GetMapping
	public ResponseEntity<BaseDataResponse<List<BookDTO>>> findBooks() {
		List<BookDTO> bookDTOList = bookService.findBooks();
		return new ResponseEntity<>(new BaseDataResponse<>(true, bookDTOList), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BaseDataResponse<BookDTO>> findBook(@PathVariable Long id) throws ServiceException {
		BookDTO bookDTO = bookService.findBook(id);
		return new ResponseEntity<>(new BaseDataResponse<>(true, bookDTO), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<BaseDataResponse<BookDTO>> createBook(@Valid @RequestBody BookDTO bookDTO) throws ServiceException {
		BookDTO createdBook = bookService.createBook(bookDTO);
		return new ResponseEntity<>(new BaseDataResponse<>(true, createdBook), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<BaseDataResponse<BookDTO>> updateBook(@PathVariable Long id,
			@Valid @RequestBody BookUpdateRequest bookUpdateRequest) throws ServiceException {
		BookDTO bookDTO = new BookDTO();
		bookDTO.setCategoryId(bookUpdateRequest.getCategoryId());
		BookDTO updatedBook = bookService.updateBook(id, bookDTO);
		return new ResponseEntity<>(new BaseDataResponse<>(true, updatedBook), HttpStatus.OK);
	}
}
