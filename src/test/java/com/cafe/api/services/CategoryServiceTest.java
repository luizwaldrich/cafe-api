package com.cafe.api.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.cafe.api.entities.Category;
import com.cafe.api.repositories.CategoryRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CategoryServiceTest {

	@MockBean
	private CategoryRepository categoryRepository;

	@Autowired
	private CategoryService categoryService;

	private static final String NAME = "Drinks";

	@Before
	public void setUp() {
		BDDMockito.given(this.categoryRepository.findByName(Mockito.anyString())).willReturn(new Category());
		BDDMockito.given(this.categoryRepository.save(Mockito.any(Category.class))).willReturn(new Category());
	}

	@Test
	public void testFindByName() {
		Optional<Category> category = this.categoryService.findByName(NAME);
		assertTrue(category.isPresent());
	}

	@Test
	public void testSaveCategory() {
		Category category = this.categoryService.persist(new Category());
		assertNotNull(category);
	}
}
