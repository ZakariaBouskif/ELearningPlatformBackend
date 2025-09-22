package com.elearningplatform.dto;

import com.elearningplatform.enumeration.LessonType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessonDto {
	
	private String title;
	
	private LessonType lessonType;
	
	private String content;
	
	private String formatedDuration;
	
	private Integer displayOrder;

	private Boolean isPublished;

	private Boolean isPreviewable;

	private Boolean isFree;
}
