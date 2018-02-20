package com.cafe.api.services;

import java.util.List;
import java.util.Optional;

import com.cafe.api.entities.Category;

public interface CategoryService {

	/**
	 * Find a category by name
	 * 
	 * @param name
	 * @return Optional<Category>
	 */
	Optional<Category> findByName(String name);

	/**
	 * Find all categories
	 * 
	 * @return
	 */
	List<Category> findAll();

	/**
	 * Persist a category in database
	 * 
	 * @param category
	 * @return Category
	 */
	Category persist(Category category);

	/**
	 * Deletes a category in database
	 * 
	 * @param id
	 */
	void delete(Long id);
}
