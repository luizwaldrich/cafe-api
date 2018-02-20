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

import com.cafe.api.entities.User;
import com.cafe.api.repositories.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {

	@MockBean
	private UserRepository userRepository;

	@Autowired
	private UserService userService;
	
	private static final String EMAIL = "test@cafe.com";

	@Before
	public void setUp() {
		BDDMockito.given(this.userRepository.findOne(Mockito.anyLong())).willReturn(new User());
		BDDMockito.given(this.userRepository.findByEmail(Mockito.anyString())).willReturn(new User());
		BDDMockito.given(this.userRepository.save(Mockito.any(User.class))).willReturn(new User());
	}

	@Test
	public void testFindOne() {
		Optional<User> user = this.userService.findOne(1L);
		assertTrue(user.isPresent());
	}
	
	@Test
	public void testFindByEmail() {
		Optional<User> user = this.userService.findByEmail(EMAIL);
		assertTrue(user.isPresent());
	}

	@Test
	public void testSaveUser() {
		User user = this.userService.persist(new User());
		assertNotNull(user);
	}
}
