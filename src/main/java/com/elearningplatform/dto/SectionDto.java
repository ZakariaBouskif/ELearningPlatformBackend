package com.elearningplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SectionDto {
	private Long id;
	private String title;
	private String description;
	private Integer displayOrder;
	private Boolean isPublished;
	private Boolean isPreviewable;
	private Boolean isShareable;
}
