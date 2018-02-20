package com.cafe.api.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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

import com.cafe.api.entities.Settings;
import com.cafe.api.repositories.SettingsRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class SettingsServiceTest {

	@MockBean
	private SettingsRepository settingsRepository;

	@Autowired
	private SettingsService settingsService;

	@Before
	public void setUp() {
		BDDMockito.given(this.settingsRepository.findOne(Mockito.anyLong())).willReturn(new Settings());
		BDDMockito.given(this.settingsRepository.save(Mockito.any(Settings.class))).willReturn(new Settings());
	}

	@Test
	public void testFindOne() {
		Optional<Settings> settings = this.settingsService.findOne(1L);
		assertTrue(settings.isPresent());
	}

	@Test
	public void testSaveSettings() {
		Settings settings = this.settingsService.persist(new Settings());
		assertNotNull(settings);
	}
}
