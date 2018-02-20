package com.cafe.api.services;

import java.util.Optional;

import com.cafe.api.entities.Company;

public interface CompanyService {

	/**
	 * Find a company by id
	 * 
	 * @param name
	 * @return Optional<Company>
	 */
	Optional<Company> findOne(Long id);

	/**
	 * Persist a company in database
	 * 
	 * @param company
	 * @return Company
	 */
	Company persist(Company company);
}
