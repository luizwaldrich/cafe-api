package com.cafe.api.services;

import java.util.Optional;

import com.cafe.api.entities.Settings;

public interface SettingsService {

	/**
	 * Find a setting by id
	 * 
	 * @param name
	 * @return Optional<Settings>
	 */
	Optional<Settings> findOne(Long id);

	/**
	 * Persist a setting in database
	 * 
	 * @param settings
	 * @return Settings
	 */
	Settings persist(Settings settings);
}
