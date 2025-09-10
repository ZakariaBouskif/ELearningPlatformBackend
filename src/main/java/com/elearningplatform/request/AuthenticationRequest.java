package com.elearningplatform.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
public class AuthenticationRequest {
	
	@NotBlank(message="VALIDATION.AUTHENTICATION.EMAIL.NOT_BLANK")
	@Email(message = "VALIDATION.AUTHENTICATION.EMAIL.FORMAT")
	@Schema(example = "mail@gmail.com")
	String email;
	
	@NotBlank(message= "VALIDATION.AUTHENTICATION.PASSWORD.NOT_BLANK")
	@Schema(example="MyPa$$wôrd!_1")
	String password;
}
