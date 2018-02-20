package com.cafe.api.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.cafe.api.entities.Banner;
import com.cafe.api.repositories.BannerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class BannerServiceTest {

	@MockBean
	private BannerRepository bannerRepository;

	@Autowired
	private BannerService bannerService;

	private static final String IMAGE_PATH = "/path/to/image.png";
	private static final String POSITION = "top";

	@Before
	public void setUp() {
		BDDMockito.given(this.bannerRepository.findByImagePath(Mockito.anyString())).willReturn(new Banner());
		BDDMockito.given(this.bannerRepository.findByPosition(Mockito.anyString())).willReturn(new ArrayList<Banner>());
		BDDMockito.given(this.bannerRepository.save(Mockito.any(Banner.class))).willReturn(new Banner());
	}

	@Test
	public void testFindByImagePath() {
		Optional<Banner> banner = this.bannerService.findByImagePath(IMAGE_PATH);
		assertTrue(banner.isPresent());
	}

	@Test
	public void testFindAllByPosition() {
		List<Banner> banners = this.bannerService.findByPosition(POSITION);
		assertNotNull(banners);
	}

	@Test
	public void testSaveBanner() {
		Banner banner = this.bannerService.persist(new Banner());
		assertNotNull(banner);
	}
}
