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

import com.cafe.api.entities.Company;
import com.cafe.api.repositories.CompanyRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CompanyServiceTest {

	@MockBean
	private CompanyRepository companyRepository;

	@Autowired
	private CompanyService companyService;

	@Before
	public void setUp() {
		BDDMockito.given(this.companyRepository.findOne(Mockito.anyLong())).willReturn(new Company());
		BDDMockito.given(this.companyRepository.save(Mockito.any(Company.class))).willReturn(new Company());
	}

	@Test
	public void testFindByName() {
		Optional<Company> company = this.companyService.findOne(1L);
		assertTrue(company.isPresent());
	}

	@Test
	public void testSaveCompany() {
		Company company = this.companyService.persist(new Company());
		assertNotNull(company);
	}
}
