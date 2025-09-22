package com.elearningplatform.dto;

import java.time.LocalDateTime;

import com.elearningplatform.entity.CourseSection;

import lombok.Data;

@Data
public class CourseSectionDto {

    private Long id;
    private Long courseId;
    private Long sectionId;
    private Integer displayOrder;
    private String sectionTitle;
    private String sectionDescription;
    private LocalDateTime assignedAt;
    
    public static CourseSectionDto fromEntity(CourseSection courseSection) {
    	CourseSectionDto dto = new CourseSectionDto();
        dto.setId(courseSection.getId());
        dto.setCourseId(courseSection.getCourse().getId());
        dto.setSectionId(courseSection.getSection().getId());
        dto.setDisplayOrder(courseSection.getDisplayOrder());
        dto.setSectionTitle(courseSection.getSection().getTitle());
        dto.setSectionDescription(courseSection.getSection().getDescription());
        dto.setAssignedAt(courseSection.getCreatedAt());
        return dto;
    }
}
