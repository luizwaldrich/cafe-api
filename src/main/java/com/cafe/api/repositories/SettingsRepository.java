package com.cafe.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cafe.api.entities.Settings;

public interface SettingsRepository extends JpaRepository<Settings, Long> {

}
