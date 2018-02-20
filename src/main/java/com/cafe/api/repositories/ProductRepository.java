package com.cafe.api.repositories;

import java.util.List;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.cafe.api.entities.Product;

@Transactional(readOnly = true)
@NamedQueries({
		@NamedQuery(name = "ProductRepository.findByCategoryId", query = "SELECT p FROM product p WHERE p.category.id = :categoryId") })
public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByCategoryId(@Param("categoryId") Long categoryId);

	Page<Product> findByCategoryId(@Param("categoryId") Long categoryId, Pageable pageable);
}
