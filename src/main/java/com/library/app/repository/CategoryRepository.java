package com.library.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.app.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

	boolean existsByNameLike(String name);

	boolean existsById(Long id);

	Optional<Category> findByName(String name);
	
}
