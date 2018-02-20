package com.cafe.api.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe.api.entities.User;
import com.cafe.api.repositories.UserRepository;
import com.cafe.api.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Optional<User> findOne(Long id) {
		User user = userRepository.findOne(id);
		return Optional.ofNullable(user);
	}
	
	@Override
	public Optional<User> findByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return Optional.ofNullable(user);
	}

	@Override
	public User persist(User user) {
		return userRepository.save(user);
	}
}
