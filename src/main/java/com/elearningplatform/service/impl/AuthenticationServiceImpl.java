package com.elearningplatform.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.elearningplatform.entity.Role;
import com.elearningplatform.entity.User;
import com.elearningplatform.enumeration.ErrorCode;
import com.elearningplatform.exception.BusinessException;
import com.elearningplatform.mapper.UserMapper;
import com.elearningplatform.repository.RoleRepository;
import com.elearningplatform.repository.UserRepository;
import com.elearningplatform.request.AuthenticationRequest;
import com.elearningplatform.request.RefreshRequest;
import com.elearningplatform.request.RegistrationRequest;
import com.elearningplatform.response.AuthenticationResponse;
import com.elearningplatform.security.JwtService;
import com.elearningplatform.service.AuthenticationService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final UserMapper userMapper;

	@Override
	public AuthenticationResponse login(AuthenticationRequest request) {
		final Authentication auth = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())

				);
		final User user = (User) auth.getPrincipal();
		final String token = jwtService.generateAccessToken(user.getUsername());
		final String refreshToken = jwtService.generateAccessToken(user.getUsername());
		final String tokenType = "Bearer";

		return AuthenticationResponse.builder().accessToken(token).refreshToken(refreshToken).tokenType(tokenType)
				.build();
	}

	@Override
	@Transactional
	public void register(RegistrationRequest request) {
		checkUserEmail(request.getEmail());
		checkPasswords(request.getPassword(), request.getConfirmPassword());

		final Role userRole = this.roleRepository.findByName("ROLE_USER")
				.orElseThrow(() -> new EntityNotFoundException("Role user does not exist"));
		final List<Role> roles = new ArrayList<>();
		roles.add(userRole);

		final User user = this.userMapper.toUser(request);
		user.setRoles(roles);
		log.debug("Saving user {}", user);
		this.userRepository.save(user);

		final List<User> users = new ArrayList<>();
		users.add(user);
		userRole.setUsers(users);

		this.roleRepository.save(userRole);

	}

	@Override
	public AuthenticationResponse refreshToken(RefreshRequest req) {
		final String newAccessToken = this.jwtService.refreshAccessToken(req.getRefreshToken());
		final String tokenType = "Bearer";
		return AuthenticationResponse.builder().accessToken(newAccessToken).refreshToken(req.getRefreshToken())
				.tokenType(tokenType).build();

	}

	private void checkUserEmail(final String email) {
		final boolean emailExists = this.userRepository.existsByEmailIgnoreCase(email);
		if (emailExists) {
			throw new BusinessException(ErrorCode.EMAIL_ALREADY_EXISTS);
		}
	}

	private void checkPasswords(final String password, final String confirmPassword) {
		if (password == null || !password.equals(confirmPassword)) {
			throw new BusinessException(ErrorCode.PASSWORD_MISMATCH);
		}
	}

}
