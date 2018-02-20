package com.cafe.api.services;

import java.util.Optional;

import com.cafe.api.entities.User;

public interface UserService {

	/**
	 * Find a user by id
	 * 
	 * @param name
	 * @return Optional<User>
	 */
	Optional<User> findOne(Long id);
	
	/**
	 * Find a user by email
	 * @param email
	 * @return Optional<User>
	 */
	Optional<User> findByEmail(String email);

	/**
	 * Persist a user in database
	 * 
	 * @param user
	 * @return User
	 */
	User persist(User user);
}
