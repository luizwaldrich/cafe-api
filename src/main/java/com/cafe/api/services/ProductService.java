package com.cafe.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cafe.api.entities.Product;

public interface ProductService {

	/**
	 * Find a product by id
	 * 
	 * @param name
	 * @return Optional<Product>
	 */
	Optional<Product> findOne(Long id);

	/**
	 * Find all products from database
	 * 
	 * @return List<Product>
	 */
	List<Product> findAll();

	/**
	 * Get all products from a Category
	 * 
	 * @param categoryId
	 * @return List<Product>
	 */
	List<Product> findByCategory(Long categoryId);

	/**
	 * Get a page of products from a Category
	 * 
	 * @param page
	 * @return Page<Product>
	 */
	Page<Product> findByCategory(Long categoryId, PageRequest page);

	/**
	 * Persist a product in database
	 * 
	 * @param product
	 * @return Product
	 */
	Product persist(Product product);

	/**
	 * Deletes a product in database
	 * 
	 * @param id
	 */
	void delete(Long id);
}
