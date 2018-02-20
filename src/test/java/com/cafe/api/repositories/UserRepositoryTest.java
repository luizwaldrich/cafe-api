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

import com.cafe.api.entities.User;
import com.cafe.api.utils.PasswordUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	private static final String EMAIL = "test@cafe.com";
	private static final String PASSWORD = "password@123456";
	private static final String FIRST_NAME = "John";
	private static final String LAST_NAME = "Doo";

	@Before
	public void setUp() throws Exception {
		User user = new User();
		user.setEmail(EMAIL);
		user.setPassword(PasswordUtils.getBCrypt(PASSWORD));
		user.setFirstName(FIRST_NAME);
		user.setLastName(LAST_NAME);
		this.userRepository.save(user);
	}

	@After
	public final void tearDown() {
		this.userRepository.deleteAll();
	}

	@Test
	public void testFindByEmail() {
		User user = this.userRepository.findByEmail(EMAIL);
		assertEquals(EMAIL, user.getEmail());
		assertNotEquals(PASSWORD, user.getPassword());
		assertEquals(FIRST_NAME, user.getFirstName());
		assertEquals(LAST_NAME, user.getLastName());
	}
}
