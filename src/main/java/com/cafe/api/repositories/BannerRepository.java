package com.cafe.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.cafe.api.entities.Banner;

@Transactional(readOnly = true)
public interface BannerRepository extends JpaRepository<Banner, Long> {	
	
	Banner findByImagePath(String imagePath);
	List<Banner> findByPosition(String position);

}
