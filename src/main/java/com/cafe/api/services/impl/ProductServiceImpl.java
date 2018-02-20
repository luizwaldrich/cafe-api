package com.cafe.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cafe.api.entities.Product;
import com.cafe.api.repositories.ProductRepository;
import com.cafe.api.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public Optional<Product> findOne(Long id) {
		Product product = productRepository.getOne(id);
		return Optional.ofNullable(product);
	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public List<Product> findByCategory(Long categoryId) {
		return productRepository.findByCategoryId(categoryId);
	}

	@Override
	public Page<Product> findByCategory(Long categoryId, PageRequest page) {
		return productRepository.findByCategoryId(categoryId, page);
	}

	@Override
	public Product persist(Product product) {
		return productRepository.save(product);
	}

	@Override
	public void delete(Long id) {
		productRepository.delete(id);		
	}

}
