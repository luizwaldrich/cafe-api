package com.cafe.api.repositories;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.cafe.api.entities.Category;
import com.cafe.api.entities.Product;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ProductRepositoryTest {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	private static final String NAME = "Cappuccino";
	private static final String IMAGE_PATH = "path/to/image.png";
	private static final String DESCRIPTION = "Best Cappuccino in the world";
	private static final String KEYWORDS = "coffee, cappuccino";
	private Long categoryId;
	
	@Before
	public void setUp() throws Exception {
		Category category = new Category();
		category.setName("Drinks");
		category.setImagePath(IMAGE_PATH);
		this.categoryRepository.save(category);
		this.categoryId = category.getId();
		
		Product product = new Product();
		product.setName(NAME);
		product.setImagePath(IMAGE_PATH);
		product.setDescription(DESCRIPTION);
		product.setCategory(category);
		product.setKeywords(KEYWORDS);
		this.productRepository.save(product);
	}
	
	@After
	public final void tearDown() {
		this.productRepository.deleteAll();
	}
	
	@Test
	public void testFindByCategory() {
		List<Product> products = this.productRepository.findByCategoryId(this.categoryId);
		assertEquals(1, products.size());
	}
	
	@Test
	public void testFindByCategoryPageable() {
		PageRequest page = new PageRequest(0, 10);
		Page<Product> products = this.productRepository.findByCategoryId(this.categoryId, page);
		assertEquals(1, products.getTotalElements());
	}
}
