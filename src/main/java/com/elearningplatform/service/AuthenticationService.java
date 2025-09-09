package com.elearningplatform.service;

import com.elearningplatform.request.AuthenticationRequest;
import com.elearningplatform.request.RefreshRequest;
import com.elearningplatform.request.RegistrationRequest;
import com.elearningplatform.response.AuthenticationResponse;

public interface AuthenticationService {
	AuthenticationResponse login(AuthenticationRequest request);

	void register(RegistrationRequest request);

	AuthenticationResponse refreshToken(RefreshRequest req);
}
