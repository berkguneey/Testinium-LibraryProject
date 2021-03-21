package com.library.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.app.dto.StoreBookDTO;
import com.library.app.dto.StoreDTO;
import com.library.app.exception.ServiceException;
import com.library.app.model.response.BaseDataResponse;
import com.library.app.model.response.BaseResponse;
import com.library.app.service.StoreService;

@RestController
@RequestMapping("api/stores")
public class StoreRestController {
	
	private StoreService storeService;

	@Autowired
	public StoreRestController(StoreService storeService) {
		this.storeService = storeService;
	}
	
	@GetMapping
	public ResponseEntity<BaseDataResponse<List<StoreDTO>>> findStores() {
		List<StoreDTO> storeDTOList = storeService.findStores();
		return new ResponseEntity<>(new BaseDataResponse<>(true, storeDTOList), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BaseDataResponse<StoreDTO>> findStore(@PathVariable Long id) throws ServiceException {
		StoreDTO storeDTO = storeService.findStore(id);
		return new ResponseEntity<>(new BaseDataResponse<>(true, storeDTO), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<BaseDataResponse<StoreDTO>> createStore(@Valid @RequestBody StoreDTO storeDTO) throws ServiceException {
		StoreDTO createdStore = storeService.createStore(storeDTO);
		return new ResponseEntity<>(new BaseDataResponse<>(true, createdStore), HttpStatus.OK);
	}

	@GetMapping("{id}/books")
	public ResponseEntity<BaseDataResponse<List<StoreBookDTO>>> getBooksByStoreId(@PathVariable Long id) throws ServiceException {
		List<StoreBookDTO> storeBookDTOList = storeService.findBooksByStoreId(id);
		return new ResponseEntity<>(new BaseDataResponse<>(true, storeBookDTOList), HttpStatus.OK);
	}
	
	@PostMapping("book")
	public ResponseEntity<BaseDataResponse<StoreBookDTO>> createBookToStore(@Valid @RequestBody StoreBookDTO storeBookDTO) throws ServiceException {
		StoreBookDTO createdStoreBook = storeService.createBookToStore(storeBookDTO);
		return new ResponseEntity<>(new BaseDataResponse<>(true, createdStoreBook), HttpStatus.OK);
	}
	
	@DeleteMapping("{storeId}/books/{bookId}")
	public ResponseEntity<BaseResponse> deleteBookFromStore(@PathVariable Long storeId, @PathVariable Long bookId) throws ServiceException {
		storeService.deleteBookFromStore(storeId, bookId);
		return new ResponseEntity<>(new BaseResponse(true, "Silme İşlemi Başarılı"), HttpStatus.OK);
	}
	

}
