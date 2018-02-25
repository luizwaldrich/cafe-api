package com.cafe.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe.api.entities.Banner;
import com.cafe.api.repositories.BannerRepository;
import com.cafe.api.services.BannerService;

@Service
public class BannerServiceImpl implements BannerService {

	private static final Logger log = LoggerFactory.getLogger(BannerServiceImpl.class);

	@Autowired
	private BannerRepository bannerRepository;
	
	@Override
	public List<Banner> findAll() {
		log.info("Searching all banners");
		return this.bannerRepository.findAll();
	}

	@Override
	public Optional<Banner> findByImagePath(String imagePath) {
		log.info("Searching banner in {}", imagePath);
		return Optional.ofNullable(bannerRepository.findByImagePath(imagePath));
	}

	@Override
	public Optional<Banner> findOne(Long id) {
		log.info("Searching banner id {}", id);
		return Optional.ofNullable(bannerRepository.findOne(id));
	}

	@Override
	public Banner persist(Banner banner) {
		log.info("Persisting banner {}", banner);
		return this.bannerRepository.save(banner);
	}

	@Override
	public List<Banner> findByPosition(String position) {
		log.info("Searching all banners with position {}", position);
		return this.bannerRepository.findByPosition(position);
	}

	@Override
	public void delete(Long id) {
		log.info("Deleting banner id {}", id);
		this.bannerRepository.delete(id);
	}
}
