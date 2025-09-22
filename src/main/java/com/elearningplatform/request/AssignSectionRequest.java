package com.elearningplatform.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record AssignSectionRequest(

		@NotNull Long courseId,

		@NotNull Long sectionId,

		@Min(0) Integer order) {

}
