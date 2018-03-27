package com.cafe.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe.api.dtos.CategoryDto;
import com.cafe.api.entities.Category;
import com.cafe.api.response.Response;
import com.cafe.api.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*")
public class CategoryController {

	private static final Logger log = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	private CategoryService categoryService;

	public CategoryController() {
	}

	/**
	 * Gets category by id
	 * 
	 * @param id
	 * @return ResponseEntity<Response<CategoryDto>>
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<CategoryDto>> get(@PathVariable("id") Long id) {
		log.info("Getting category id: {}", id);
		Response<CategoryDto> response = new Response<CategoryDto>();
		Optional<Category> category = this.categoryService.findOne(id);

		if (!category.isPresent()) {
			log.info("Category id {} does not exist.", id);
			response.getErrors().add("O category informado não existe.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(this.convertCategoryToDto(category.get()));
		return ResponseEntity.ok(response);
	}

	/**
	 * Gets all categories
	 * 
	 * @return ResponseEntity<Response<List<CategoryDto>>>
	 */
	@GetMapping
	public ResponseEntity<Response<List<CategoryDto>>> getAll() {
		log.info("Getting all categories");
		Response<List<CategoryDto>> response = new Response<List<CategoryDto>>();
		List<Category> categories = this.categoryService.findAll();

		List<CategoryDto> categoriesDto = new ArrayList<CategoryDto>();
		categories.forEach(category -> categoriesDto.add(this.convertCategoryToDto(category)));
		response.setData(categoriesDto);
		return ResponseEntity.ok(response);
	}

	/**
	 * Saves a category in database
	 * 
	 * @param categoryDto
	 * @param result
	 * @return ResponseEntity<Response<CategoryDto>>
	 */
	@PostMapping
	public ResponseEntity<Response<CategoryDto>> save(@Valid @RequestBody CategoryDto categoryDto, BindingResult result) {
		log.info("Saving category: {}", categoryDto.toString());
		Response<CategoryDto> response = new Response<CategoryDto>();

		if (result.hasErrors()) {
			log.error("{}", result.getAllErrors());
			result.getAllErrors().forEach(erro -> response.getErrors().add(erro.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		Category category = this.convertDtoToCategory(categoryDto);
		this.categoryService.persist(category);

		response.setData(this.convertCategoryToDto(category));
		return ResponseEntity.ok(response);
	}

	/**
	 * Deletes a category from database
	 * 
	 * @param id
	 * @return ResponseEntity<Response<String>>
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<String>> delete(@PathVariable("id") Long id) {
		log.info("Removing category id: {}", id);
		Response<String> response = new Response<String>();
		Optional<Category> category = this.categoryService.findOne(id);

		if (!category.isPresent()) {
			log.info("Category id {} does not exist.", id);
			response.getErrors().add("O category informado não existe.");
			return ResponseEntity.badRequest().body(response);
		}

		this.categoryService.delete(id);
		return ResponseEntity.ok(new Response<String>());
	}

	/**
	 * Converts DTO into a persisted category
	 * 
	 * @param categoryDto
	 * @return
	 */
	private Category convertDtoToCategory(CategoryDto categoryDto) {
		Category category = new Category();
		category.setImagePath(categoryDto.getImagePath());
		category.setName(categoryDto.getName());
		return category;
	}

	/**
	 * Converts a persisted category into DTO
	 * 
	 * @param category
	 * @return
	 */
	private CategoryDto convertCategoryToDto(Category category) {
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setId(category.getId());
		categoryDto.setImagePath(category.getImagePath());
		categoryDto.setName(category.getName());
		return categoryDto;
	}

}
