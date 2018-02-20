package com.cafe.api.utils;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtilsTest {
	
	private static final String PASSWORD = "password@123456";
	private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Test
	public void testNullPassword() throws Exception {
		assertNull(PasswordUtils.getBCrypt(null));
	}
	
	@Test
	public void testGenerateHash() throws Exception {
		String hash = PasswordUtils.getBCrypt(PASSWORD);
		assertTrue(encoder.matches(PASSWORD, hash));
	}

}
