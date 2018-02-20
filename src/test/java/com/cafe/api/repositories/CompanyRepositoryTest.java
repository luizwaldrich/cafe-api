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

import com.cafe.api.entities.Company;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CompanyRepositoryTest {

	@Autowired
	private CompanyRepository companyRepository;
	
	private static final String NAME = "Food";
	private static final String ADDRESS = "Street, 123";
	private static final String DESCRIPTION = "Best Cafe in the world";
	private static final String EMAIL = "contact@cafe.com.br";
	private static final String PHONE = "999999999";
	
	@Before
	public void setUp() throws Exception {
		Company company = new Company();
		company.setName(NAME);
		company.setAddress(ADDRESS);
		company.setDescription(DESCRIPTION);
		company.setEmail(EMAIL);
		company.setPhone(PHONE);
		company.setSlogan(DESCRIPTION);
		this.companyRepository.save(company);
	}
	
	@After
	public final void tearDown() {
		this.companyRepository.deleteAll();
	}
	
	@Test
	public void testFindById() {
		Company company = this.companyRepository.findOne(1L);
		assertEquals(NAME, company.getName());
		assertEquals(ADDRESS, company.getAddress());
		assertEquals(DESCRIPTION, company.getDescription());
		assertEquals(EMAIL, company.getEmail());
		assertEquals(PHONE, company.getPhone());
		assertEquals(DESCRIPTION, company.getSlogan());
	}
}
