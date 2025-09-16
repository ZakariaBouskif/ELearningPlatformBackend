package com.elearningplatform.request;

import java.time.LocalDate;

import com.elearningplatform.entity.AddressInfo;
import com.elearningplatform.enumeration.Gender;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record StudentRequest(
	@NotBlank
	@Size(max=50)
	@Schema(example="first name")
	String firstName,
	
	@NotBlank
	@Size(max=50)
	@Schema(example="last name")
	String lastName,
	
	@NotNull (message="Gender is required")
	@Schema(
			example="MALE",
			allowableValues = {"MALE", "FEMALE"}
			)
	Gender gender,
	
	@NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
	@Schema(description = "Date of birth must be in the past", example = "yyyy-mm-dd", type = "string", format = "date")
    LocalDate dateOfBirth,
    
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number must be valid")
	@Schema(example="+212600000000")
    String phone,
    
    @Valid
    @NotNull(message = "Address is required")
    AddressInfo address,
    
    @Size(max = 1000, message = "Bio cannot exceed 1000 characters")
    String bio,
    
    @Size(max=50)
    String occupation,
    
    @Size(max=100)
    String educationLevel,
    
    @Size(max=100)
    String interests
        
) {}
