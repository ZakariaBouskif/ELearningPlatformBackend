package com.elearningplatform.request;

import jakarta.validation.constraints.NotNull;

public record CourseSectionRequest(
		
		@NotNull
		Long courseId,
		
		@NotNull
		Long sectionId,
		
		Integer displayOrder
		
		
		) {

}
