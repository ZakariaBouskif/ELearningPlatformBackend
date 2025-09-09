package com.elearningplatform.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.elearningplatform.entity.User;
import com.elearningplatform.request.ChangePasswordRequest;
import com.elearningplatform.request.ProfileUpdateRequest;
import com.elearningplatform.service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "User", description = "User API")
public class UserController {

	private final UserService userService;

	@PatchMapping("/me")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void updateProfile(@RequestBody @Valid final ProfileUpdateRequest request, final Authentication principal) {
		userService.updateprofileInfo(request, getUserId(principal));
	}

	@PostMapping("/me/password")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void changePassword(@RequestBody @Valid final ChangePasswordRequest request,
			final Authentication principal) {
		userService.changePassword(request, getUserId(principal));
	}

	@PatchMapping("/me/toggle-activation")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void toggleActivationAccount(final Authentication principal) {
		userService.toggleActivationAccount(getUserId(principal));
	}


	private Long getUserId(final Authentication authentication) {
		return ((User) authentication.getPrincipal()).getId();
	}

}
