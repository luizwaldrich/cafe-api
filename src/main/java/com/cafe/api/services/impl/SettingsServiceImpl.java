package com.cafe.api.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe.api.entities.Settings;
import com.cafe.api.repositories.SettingsRepository;
import com.cafe.api.services.SettingsService;

@Service
public class SettingsServiceImpl implements SettingsService {

	@Autowired
	private SettingsRepository settingsRepository;

	@Override
	public Optional<Settings> findOne(Long id) {
		Settings settings = settingsRepository.findOne(id);
		return Optional.ofNullable(settings);
	}

	@Override
	public Settings persist(Settings settings) {
		return settingsRepository.save(settings);
	}
}
