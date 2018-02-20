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

import com.cafe.api.entities.Settings;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class SettingsRepositoryTest {

	@Autowired
	private SettingsRepository settingsRepository;

	private static final String ABOUT_US = "We have the best cappuccino in the world";
	private static final String CONTACT_US = "Please, contact us by filling the form bellow";
	private static final Boolean SHOW_PRICE = true;

	@Before
	public void setUp() throws Exception {
		Settings settings = new Settings();
		settings.setAboutUs(ABOUT_US);
		settings.setContactUsText(CONTACT_US);
		settings.setShowPrice(SHOW_PRICE);
		this.settingsRepository.save(settings);
	}

	@After
	public final void tearDown() {
		this.settingsRepository.deleteAll();
	}

	@Test
	public void testFindById() {
		Settings settings = this.settingsRepository.findOne(1L);
		assertEquals(ABOUT_US, settings.getAboutUs());
		assertEquals(CONTACT_US, settings.getContactUsText());
		assertEquals(SHOW_PRICE, settings.getShowPrice());
	}
}
