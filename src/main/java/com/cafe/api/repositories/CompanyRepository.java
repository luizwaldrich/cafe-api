package com.cafe.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cafe.api.entities.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
