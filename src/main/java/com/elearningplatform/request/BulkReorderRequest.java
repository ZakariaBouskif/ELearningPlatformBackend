package com.elearningplatform.request;

import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record BulkReorderRequest(
		@NotNull
	    Long courseId,
	    
	    @NotNull
	    List<ReorderItem> items
		
		) {

	public  record ReorderItem (
	    @NotNull
	    Long courseSectionId,
	    
	    @NotNull
	    @Min(0)
	    Integer newOrder
	) {}
}


