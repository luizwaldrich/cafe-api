package com.cafe.api.services;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.cafe.api.entities.Product;
import com.cafe.api.repositories.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ProductServiceTest {

	@MockBean
	private ProductRepository productRepository;

	@Autowired
	private ProductService productService;
	
	@Before
	public void setUp() {
		BDDMockito.given(this.productRepository.findByCategoryId(Mockito.anyLong()))
				.willReturn(new ArrayList<Product>());
		BDDMockito.given(this.productRepository.findByCategoryId(Mockito.anyLong(), Mockito.any(PageRequest.class)))
				.willReturn(new PageImpl<Product>(new ArrayList<Product>()));
		BDDMockito.given(this.productRepository.save(Mockito.any(Product.class))).willReturn(new Product());
	}

	@Test
	public void testFindByCategory() {
		List<Product> products = productService.findByCategory(1L);
		assertNotNull(products);
	}
	
	@Test
	public void testFindByCategoryPageable() {
		PageRequest page = new PageRequest(0, 10);
		Page<Product> products = productService.findByCategory(1L, page);
		assertNotNull(products);
	}

	@Test
	public void testSaveProduct() {
		Product product = this.productService.persist(new Product());
		assertNotNull(product);
	}
}
