package com.elearningplatform.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ReorderRequest(
		
		@NotNull
		Long courseSectionId,
		
		@NotNull
		@Min(0)
		Integer newOrder
		
		) {

}
