package com.elearningplatform.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.elearningplatform.request.ChangePasswordRequest;
import com.elearningplatform.request.ProfileUpdateRequest;

public interface UserService extends UserDetailsService{
	void updateprofileInfo(ProfileUpdateRequest request, Long userId);
	void changePassword(ChangePasswordRequest request, Long userId);
	void toggleActivationAccount(Long userId);
	void deleteAccount(Long userId);
}
