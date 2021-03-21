package com.library.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.app.dto.StoreBookDTO;
import com.library.app.dto.StoreDTO;
import com.library.app.exception.DataConflictException;
import com.library.app.exception.Error;
import com.library.app.exception.NotFoundException;
import com.library.app.exception.ServiceException;
import com.library.app.mapper.BaseMapper;
import com.library.app.model.Book;
import com.library.app.model.Store;
import com.library.app.model.StoreBook;
import com.library.app.repository.BookRepository;
import com.library.app.repository.StoreBookRepository;
import com.library.app.repository.StoreRepository;
import com.library.app.service.StoreService;

@Service
public class StoreServiceImpl implements StoreService {

	private BookRepository bookRepository;
	private StoreRepository storeRepository;
	private StoreBookRepository storeBookRepository;

	@Autowired
	public StoreServiceImpl(BookRepository bookRepository, StoreRepository storeRepository,
			StoreBookRepository storeBookRepository) {
		this.bookRepository = bookRepository;
		this.storeRepository = storeRepository;
		this.storeBookRepository = storeBookRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<StoreDTO> findStores() {
		List<Store> storeList = storeRepository.findAll();
		return BaseMapper.mapAll(storeList, StoreDTO.class);
	}
	
	@Override
	@Transactional(readOnly = true)
	public StoreDTO findStore(Long id) throws ServiceException {
		Store store = findStoreEntityById(id);
		return BaseMapper.map(store, StoreDTO.class);
	}

	@Override
	@Transactional(rollbackFor = ServiceException.class)
	public StoreDTO createStore(StoreDTO storeDTO) throws ServiceException {
		if (storeRepository.existsByNameAndCityLike(storeDTO.getName(), storeDTO.getCity())) {
			throw new DataConflictException(Error.STORE_IS_ALREADY_EXIST);
		}
		Store store = BaseMapper.map(storeDTO, Store.class);
		storeRepository.save(store);
		return BaseMapper.map(store, StoreDTO.class);
	}

	@Override
	@Transactional(readOnly = true)
	public List<StoreBookDTO> findBooksByStoreId(Long id) throws ServiceException {
		if(!storeRepository.existsById(id)) {
			throw new NotFoundException(Error.STORE_NOT_FOUND);
		}
		List<StoreBook> storeBookList = storeBookRepository.findByStoreId(id);
		return BaseMapper.mapAll(storeBookList, StoreBookDTO.class);
	}

	@Override
	@Transactional(rollbackFor = ServiceException.class)
	public StoreBookDTO createBookToStore(StoreBookDTO storeBookDTO) throws ServiceException {
		Book book = bookRepository.findById(storeBookDTO.getBookId())
				.orElseThrow(() -> new NotFoundException(Error.BOOK_NOT_FOUND));

		Store store = findStoreEntityById(storeBookDTO.getStoreId());

		if (storeBookRepository.existsByStoreIdAndBookId(storeBookDTO.getStoreId(), storeBookDTO.getBookId())) {
			throw new DataConflictException(Error.BOOK_IS_ALREADY_EXIST);
		}

		StoreBook storeBook = BaseMapper.map(storeBookDTO, StoreBook.class);
		storeBook.setBook(book);
		storeBook.setStore(store);
		storeBookRepository.save(storeBook);
		return BaseMapper.map(storeBook, StoreBookDTO.class);
	}

	@Override
	@Transactional()
	public void deleteBookFromStore(Long storeId, Long bookId) throws ServiceException {
		StoreBook storeBook = storeBookRepository.findByStoreIdAndBookId(storeId, bookId);
		if (storeBook == null) {
			throw new NotFoundException(Error.BOOK_OR_CATEGORY_NOT_FOUND);
		}
		storeBookRepository.delete(storeBook);
	}
	
	private Store findStoreEntityById(Long id) throws ServiceException {
		return storeRepository.findById(id).orElseThrow(() -> new NotFoundException(Error.STORE_NOT_FOUND));
	}

}
