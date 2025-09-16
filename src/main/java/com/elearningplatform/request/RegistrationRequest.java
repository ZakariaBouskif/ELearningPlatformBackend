package com.elearningplatform.request;

import com.elearningplatform.validation.NonDisposableEmail;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationRequest {
	
	@NotBlank(message = "VALIDATION.REGISTRATION.EMAIL.BLANK")
	@Email(message = "VALIDATION.REGISTRATION.EMAIL.FORMAT")
	@NonDisposableEmail(message = "VALIDATION.REGISTRATION.EMAIL.DISPOSABLE")
	@Schema(example = "mail@gmail.com")
	String email;

	
	@NotBlank(message = "VALIDATION.REGISTRATION.PASSWORD.BLANK")
	@Size(min = 8, max = 72, message = "VALIDATION.REGISTRATION.PASSWORD.SIZE")
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*\\W).*$", message = "VALIDATION.REGISTRATION.PASSWORD.WEAK")
	@Schema(example = "MyPa$$wôrd!_1")
	String password;

	@NotBlank(message = "VALIDATION.REGISTRATION.CONFIRM_PASSWORD.BLANK")
	@Size(min = 8, max = 72, message = "VALIDATION.REGISTRATION.CONFIRM_PASSWORD.SIZE")
	@Schema(example = "MyPa$$wôrd!_1")
	String confirmPassword;

}
