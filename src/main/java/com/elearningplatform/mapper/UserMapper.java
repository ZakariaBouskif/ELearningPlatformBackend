package com.elearningplatform.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.elearningplatform.entity.User;
import com.elearningplatform.request.RegistrationRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserMapper {
	private final PasswordEncoder passwordEncoder;

	public User toUser(final RegistrationRequest request) {
		return User.builder()
					.email(request.getEmail())
					.password(passwordEncoder.encode(request.getPassword()))
					.enabled(true).locked(false)
					.credentialsExpired(false)
					.emailVerified(false)
					.phoneVerified(false)
					.build();
	}
}
