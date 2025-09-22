package com.elearningplatform.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SectionRequest(
		
		@NotNull
		@Size(min=3)
		String title,
		
		String description,
		
		Integer displayOrder,
		
		Boolean isPublished,
		
		Boolean isPreviewable,
		
		Boolean isShareable,
		
		@NotNull
		Long courseId
		
		) {

}
