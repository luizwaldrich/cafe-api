package com.cafe.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe.api.dtos.BannerDto;
import com.cafe.api.entities.Banner;
import com.cafe.api.response.Response;
import com.cafe.api.services.BannerService;

@RestController
@RequestMapping("/api/banners")
@CrossOrigin(origins = "*")
public class BannerController {

	private static final Logger log = LoggerFactory.getLogger(BannerController.class);

	@Autowired
	private BannerService bannerService;

	public BannerController() {
	}

	/**
	 * Gets banner by id
	 * 
	 * @param id
	 * @return ResponseEntity<Response<BannerDto>>
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<BannerDto>> get(@PathVariable("id") Long id) {
		log.info("Getting banner id: {}", id);
		Response<BannerDto> response = new Response<BannerDto>();
		Optional<Banner> banner = this.bannerService.findOne(id);

		if (!banner.isPresent()) {
			log.info("Banner id {} does not exist.", id);
			response.getErrors().add("O banner informado não existe.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(this.convertBannerToDto(banner.get()));
		return ResponseEntity.ok(response);
	}

	/**
	 * Gets banners by position
	 * 
	 * @param position
	 * @return ResponseEntity<Response<List<BannerDto>>>
	 */
	@GetMapping(value = "/position/{position}")
	public ResponseEntity<Response<List<BannerDto>>> get(@PathVariable("position") String position) {
		log.info("Getting banner position: {}", position);
		Response<List<BannerDto>> response = new Response<List<BannerDto>>();
		List<Banner> banners = this.bannerService.findByPosition(position);

		List<BannerDto> bannersDto = new ArrayList<BannerDto>();
		banners.forEach(banner -> bannersDto.add(this.convertBannerToDto(banner)));
		response.setData(bannersDto);
		return ResponseEntity.ok(response);
	}

	/**
	 * Gets all banners
	 * 
	 * @return ResponseEntity<Response<List<BannerDto>>>
	 */
	@GetMapping
	public ResponseEntity<Response<List<BannerDto>>> getAll() {
		log.info("Getting all banners");
		Response<List<BannerDto>> response = new Response<List<BannerDto>>();
		List<Banner> banners = this.bannerService.findAll();

		List<BannerDto> bannersDto = new ArrayList<BannerDto>();
		banners.forEach(banner -> bannersDto.add(this.convertBannerToDto(banner)));
		response.setData(bannersDto);
		return ResponseEntity.ok(response);
	}

	/**
	 * Saves a banner in database
	 * 
	 * @param bannerDto
	 * @param result
	 * @return ResponseEntity<Response<BannerDto>>
	 */
	@PostMapping
	public ResponseEntity<Response<BannerDto>> save(@Valid @RequestBody BannerDto bannerDto, BindingResult result) {
		log.info("Saving banner: {}", bannerDto.toString());
		Response<BannerDto> response = new Response<BannerDto>();
		validateIfExists(bannerDto, result);

		if (result.hasErrors()) {
			log.error("{}", result.getAllErrors());
			result.getAllErrors().forEach(erro -> response.getErrors().add(erro.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		Banner banner = this.convertDtoToBanner(bannerDto);
		this.bannerService.persist(banner);

		response.setData(this.convertBannerToDto(banner));
		return ResponseEntity.ok(response);
	}

	/**
	 * Deletes a banner from database
	 * 
	 * @param id
	 * @return ResponseEntity<Response<String>>
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<String>> delete(@PathVariable("id") Long id) {
		log.info("Removing banner id: {}", id);
		Response<String> response = new Response<String>();
		Optional<Banner> banner = this.bannerService.findOne(id);

		if (!banner.isPresent()) {
			log.info("Banner id {} does not exist.", id);
			response.getErrors().add("O banner informado não existe.");
			return ResponseEntity.badRequest().body(response);
		}

		this.bannerService.delete(id);
		return ResponseEntity.ok(new Response<String>());
	}

	/**
	 * Validates if a banner already exists for this image path.
	 * 
	 * @param bannerDto
	 * @param result
	 */
	private void validateIfExists(BannerDto bannerDto, BindingResult result) {
		this.bannerService.findByImagePath(bannerDto.getImagePath()).ifPresent(
				banner -> result.addError(new ObjectError("banner", "Já existe um banner com esta imagem.")));
	}

	/**
	 * Converts DTO into a persisted banner
	 * 
	 * @param bannerDto
	 * @return
	 */
	private Banner convertDtoToBanner(BannerDto bannerDto) {
		Banner banner = new Banner();
		banner.setImagePath(bannerDto.getImagePath());
		banner.setPosition(bannerDto.getPosition());
		return banner;
	}

	/**
	 * Converts a persisted banner into DTO
	 * 
	 * @param banner
	 * @return
	 */
	private BannerDto convertBannerToDto(Banner banner) {
		BannerDto bannerDto = new BannerDto();
		bannerDto.setId(banner.getId());
		bannerDto.setImagePath(banner.getImagePath());
		bannerDto.setPosition(banner.getPosition());
		return bannerDto;
	}

}
