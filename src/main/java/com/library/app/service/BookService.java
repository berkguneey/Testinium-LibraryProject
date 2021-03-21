package com.library.app.service;

import java.util.List;

import com.library.app.dto.BookDTO;
import com.library.app.exception.ServiceException;


public interface BookService {

	List<BookDTO> findBooks();
	BookDTO findBook(Long id) throws ServiceException;
	BookDTO createBook(BookDTO bookDTO) throws ServiceException;
	BookDTO updateBook(Long id, BookDTO bookDTO) throws ServiceException;
	
}
