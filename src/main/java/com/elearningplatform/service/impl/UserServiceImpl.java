package com.elearningplatform.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.elearningplatform.entity.User;
import com.elearningplatform.enumeration.ErrorCode;
import com.elearningplatform.exception.BusinessException;
import com.elearningplatform.repository.UserRepository;
import com.elearningplatform.request.ChangePasswordRequest;
import com.elearningplatform.service.UserService;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		return userRepository.findByEmailIgnoreCase(userEmail)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + userEmail));
	}


	@Override
	public void changePassword(ChangePasswordRequest request, Long userId) {
		if (!request.getNewPassword().equals(request.getConfirmNewPassword())) {
			throw new BusinessException(ErrorCode.CHANGE_PASSWORD_MISMATCH);
		}

		final User savedUser = userRepository.findById(userId)
				.orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
		
		if(passwordEncoder.matches(request.getCurrentPassword(), savedUser.getPassword())) {
			throw new BusinessException(ErrorCode.INVALID_CURRENT_PASSWORD);
		}
		
		final String encoded = passwordEncoder.encode(request.getNewPassword());
		savedUser.setPassword(encoded);
		userRepository.save(savedUser);
	}

	@Override
	public void toggleActivationAccount(Long userId) {
		final User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
		user.setEnabled(!user.isEnabled());
		userRepository.save(user);
	}

	@Override
	public void deleteAccount(Long userId) {
		

	}

}
