package com.cafe.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe.api.entities.Category;
import com.cafe.api.repositories.CategoryRepository;
import com.cafe.api.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Optional<Category> findByName(String name) {
		Category category = categoryRepository.findByName(name);
		return Optional.ofNullable(category);
	}

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Category persist(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public void delete(Long id) {
		categoryRepository.delete(id);
	}

}
