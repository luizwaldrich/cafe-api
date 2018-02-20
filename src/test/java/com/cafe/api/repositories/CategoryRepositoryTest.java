package com.cafe.api.repositories;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.cafe.api.entities.Category;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CategoryRepositoryTest {

	@Autowired
	private CategoryRepository categoryRepository;
	
	private static final String NAME = "Doces";
	private static final String IMAGE_PATH = "path/to/image.png";
	
	@Before
	public void setUp() throws Exception {
		Category category = new Category();
		category.setName(NAME);
		category.setImagePath(IMAGE_PATH);
		this.categoryRepository.save(category);
	}
	
	@After
	public final void tearDown() {
		this.categoryRepository.deleteAll();
	}
	
	@Test
	public void testFindByName() {
		Category category = this.categoryRepository.findByName(NAME);
		assertEquals(NAME, category.getName());
		assertEquals(IMAGE_PATH, category.getImagePath());
	}
}
