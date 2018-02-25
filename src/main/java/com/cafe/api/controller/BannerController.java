package com.cafe.api.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
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

	private void validateIfExists(BannerDto bannerDto, BindingResult result) {
		this.bannerService.findByImagePath(bannerDto.getImagePath())
				.ifPresent(banner -> result.addError(new ObjectError("banner", "Já existe um banner com esta imagem.")));
	}

	private Banner convertDtoToBanner(BannerDto bannerDto) {
		Banner banner = new Banner();
		banner.setImagePath(bannerDto.getImagePath());
		banner.setPosition(bannerDto.getPosition());
		return banner;
	}
	
	private BannerDto convertBannerToDto(Banner banner) {
		BannerDto bannerDto = new BannerDto();
		bannerDto.setId(banner.getId());
		bannerDto.setImagePath(banner.getImagePath());
		bannerDto.setPosition(banner.getPosition());
		return bannerDto;
	}

}
