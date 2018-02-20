package com.cafe.api.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe.api.entities.Company;
import com.cafe.api.repositories.CompanyRepository;
import com.cafe.api.services.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {
	
	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public Optional<Company> findOne(Long id) {
		Company company = companyRepository.findOne(id);
		return Optional.ofNullable(company);
	}

	@Override
	public Company persist(Company company) {
		return companyRepository.save(company);
	}
}
