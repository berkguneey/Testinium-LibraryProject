package com.library.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.app.model.StoreBook;

@Repository
public interface StoreBookRepository extends JpaRepository<StoreBook, Long>{
	Optional<StoreBook> findByBookId(Long id);
	List<StoreBook> findByStoreId(Long id);
	boolean existsByStoreIdAndBookId(Long storeId, Long bookId);
	StoreBook findByStoreIdAndBookId(Long storeId, Long bookId);
}
