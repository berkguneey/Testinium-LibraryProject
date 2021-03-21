package com.library.app.service;

import java.util.List;

import com.library.app.dto.StoreBookDTO;
import com.library.app.dto.StoreDTO;
import com.library.app.exception.ServiceException;

public interface StoreService {

	List<StoreDTO> findStores();
	StoreDTO findStore(Long id) throws ServiceException;
	StoreDTO createStore(StoreDTO storeDTO) throws ServiceException;
	List<StoreBookDTO> findBooksByStoreId(Long id) throws ServiceException;
	StoreBookDTO createBookToStore(StoreBookDTO storeBookDTO) throws ServiceException;
	void deleteBookFromStore(Long storeId, Long bookId) throws ServiceException;
	
}
