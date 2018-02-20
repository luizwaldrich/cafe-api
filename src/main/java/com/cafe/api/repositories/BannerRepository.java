package com.cafe.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.cafe.api.entities.Banner;

public interface BannerRepository extends JpaRepository<Banner, Long> {
	
	@Transactional(readOnly = true)
	Banner findByImagePath(String imagePath);

}
