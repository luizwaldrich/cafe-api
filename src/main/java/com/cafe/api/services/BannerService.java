package com.cafe.api.services;

import java.util.List;
import java.util.Optional;

import com.cafe.api.entities.Banner;

public interface BannerService {

	/**
	 * Find a banner given image path
	 * 
	 * @param imagePath
	 * @return Optional<Banner>
	 */
	List<Banner> findAll();
	
	/**
	 * Find a banner given image path
	 * 
	 * @param imagePath
	 * @return Optional<Banner>
	 */
	Optional<Banner> findByImagePath(String imagePath);
	
	/**
	 * Find banner by id
	 * @param id
	 * @return
	 */
	Optional<Banner> findOne(Long id);

	/**
	 * Find all banners with same position
	 * 
	 * @param position
	 * @return Optional<List<Banner>>
	 */
	List<Banner> findByPosition(String position);

	/**
	 * Persist a banner in database
	 * 
	 * @param banner
	 * @return Banner
	 */
	Banner persist(Banner banner);

	/**
	 * Removes a banner from database
	 * 
	 * @param id
	 */
	void delete(Long id);
}
