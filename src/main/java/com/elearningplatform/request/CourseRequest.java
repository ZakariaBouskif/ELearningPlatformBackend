package com.elearningplatform.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.elearningplatform.enumeration.ElementLevel;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CourseRequest(
		@NotBlank
		@Size(min = 3, max = 50)
		String title,
		
		@NotBlank
		@Size(min = 3, max = 50)
		String subtitle,
		
		@NotNull
		Long categoryCourse,
		
		@NotBlank
		@Size(min=2, max =2)
		@Schema(example="AR")
		String language,
		
		@Schema(
	        description = "The level of the course",
	        example = "BEGINNER",
	        allowableValues = {"BEGINNER", "INTERMEDIATE", "ADVANCED"}
		)
		ElementLevel courseLevel,
		
		String thumbnailUrl,
		
		String promoVideoUrl,
		
		
		@Schema(description = "Price of the course", example = "0.00")
	    @NotNull(message = "Price is required")
		@DecimalMin(value = "0.00", inclusive = true, message = "Price must be greater than 0")
	    @DecimalMax(value = "10000.00", message = "Price must be less than or equal to 10000")
	    @Digits(integer = 6, fraction = 2, message = "Price format is invalid (max 6 digits and 2 decimals)")
		BigDecimal price,
		
		@Schema(description = "Published date", example = "yyyy-MM-dd'T'HH:mm:ss", type = "string", format = "date-time")
		LocalDateTime publishedAt
		
		) {

}
