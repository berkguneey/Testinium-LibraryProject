package com.library.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.app.model.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long>{

	boolean existsByNameAndCityLike(String name, String city);
	
}
