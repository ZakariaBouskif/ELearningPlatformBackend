package com.elearningplatform.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SectionWithLessonsDto {
	private Long id;
    private String title;
    private List<LessonDto> lessons;
}
