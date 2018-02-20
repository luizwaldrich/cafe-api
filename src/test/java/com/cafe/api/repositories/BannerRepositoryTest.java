package com.cafe.api.repositories;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.cafe.api.entities.Banner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class BannerRepositoryTest {

	@Autowired
	private BannerRepository bannerRepository;
	
	private static final String IMAGE_PATH = "path/to/image.png";
	
	@Before
	public void setUp() throws Exception {
		Banner banner = new Banner();
		banner.setImagePath(IMAGE_PATH);
		banner.setPosition("main");
		this.bannerRepository.save(banner);
	}
	
	@After
	public final void tearDown() {
		this.bannerRepository.deleteAll();
	}
	
	@Test
	public void testFindByImagePath() {
		Banner banner = this.bannerRepository.findByImagePath(IMAGE_PATH);
		
		assertEquals(IMAGE_PATH, banner.getImagePath());
	}

}
