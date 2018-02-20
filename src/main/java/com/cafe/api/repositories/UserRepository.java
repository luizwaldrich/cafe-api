package com.cafe.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.cafe.api.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Transactional(readOnly = true)
	User findByEmail(String email);
}
