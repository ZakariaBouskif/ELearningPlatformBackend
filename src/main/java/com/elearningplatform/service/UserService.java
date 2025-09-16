package com.elearningplatform.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.elearningplatform.request.ChangePasswordRequest;

public interface UserService extends UserDetailsService{
	void changePassword(ChangePasswordRequest request, Long userId);
	void toggleActivationAccount(Long userId);
	void deleteAccount(Long userId);
}
