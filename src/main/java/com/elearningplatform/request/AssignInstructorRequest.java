package com.elearningplatform.request;

import jakarta.validation.constraints.NotNull;

public record AssignInstructorRequest(
		@NotNull
		Long instructorId,
		
		Boolean primaryInstructor
		
		) {

}
