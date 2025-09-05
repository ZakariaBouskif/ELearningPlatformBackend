package com.elearningplatform.security;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
	
	
	@Override
	protected void doFilterInternal(
			@NonNull
			final HttpServletRequest request,
			@NonNull
			final HttpServletResponse response,
			@NonNull
			final FilterChain filterChain) throws ServletException, IOException {
	
		if(request.getServletPath().contains("/api/v1/auth")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		final String jwt;
		final String username;
		
		if(authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		jwt = authHeader.substring(7);

		
	}

}
