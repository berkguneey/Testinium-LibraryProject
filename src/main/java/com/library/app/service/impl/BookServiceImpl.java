package com.library.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.app.dto.BookDTO;
import com.library.app.exception.DataConflictException;
import com.library.app.exception.Error;
import com.library.app.exception.NotFoundException;
import com.library.app.exception.ServiceException;
import com.library.app.mapper.BaseMapper;
import com.library.app.model.Book;
import com.library.app.model.Category;
import com.library.app.repository.BookRepository;
import com.library.app.repository.CategoryRepository;
import com.library.app.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;
	private final CategoryRepository categoryRepository;

	@Autowired
	public BookServiceImpl(BookRepository bookRepository, CategoryRepository categoryRepository) {
		this.bookRepository = bookRepository;
		this.categoryRepository = categoryRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<BookDTO> findBooks() {
		List<Book> bookList = bookRepository.findAll();
		return BaseMapper.mapAll(bookList, BookDTO.class);
	}
	
	@Override
	@Transactional(readOnly = true)
	public BookDTO findBook(Long id) throws ServiceException {
		Book book = findBookEntityById(id);
		return BaseMapper.map(book, BookDTO.class);
	}

	@Override
	@Transactional(rollbackFor = ServiceException.class)
	public BookDTO createBook(BookDTO bookDTO) throws ServiceException {
		if (bookRepository.existsByNameLike(bookDTO.getName())) {
			throw new DataConflictException(Error.BOOK_IS_ALREADY_EXIST);
		}

		Category category = categoryRepository.findById(bookDTO.getCategoryId())
				.orElseThrow(() -> new NotFoundException(Error.CATEGORY_NOT_FOUND));

		Book book = BaseMapper.map(bookDTO, Book.class);
		book.setCategory(category);

		bookRepository.save(book);
		return BaseMapper.map(book, BookDTO.class);
	}

	@Override
	@Transactional(rollbackFor = ServiceException.class)
	public BookDTO updateBook(Long id, BookDTO bookDTO) throws ServiceException {
		Book book = findBookEntityById(id);

		Category category = categoryRepository.findById(bookDTO.getCategoryId())
				.orElseThrow(() -> new NotFoundException(Error.CATEGORY_NOT_FOUND));

		book.setCategory(category);
		bookRepository.save(book);
		return BaseMapper.map(book, BookDTO.class);

	}
	
	private Book findBookEntityById(Long id) throws ServiceException {
		return bookRepository.findById(id).orElseThrow(() -> new NotFoundException(Error.BOOK_NOT_FOUND));
	}

}
