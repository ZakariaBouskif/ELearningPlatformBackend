package com.elearningplatform.response;

import java.time.LocalDateTime;

import com.elearningplatform.entity.CourseSection;

import lombok.Data;

@Data
public class OrderResponse {
    private Long courseSectionId;
    private Long sectionId;
    private Integer oldOrder;
    private Integer newOrder;
    private LocalDateTime updatedAt;
    
    public OrderResponse(Long courseSectionId, Long sectionId, Integer oldOrder, Integer newOrder) {
        this.courseSectionId = courseSectionId;
        this.sectionId = sectionId;
        this.oldOrder = oldOrder;
        this.newOrder = newOrder;
        this.updatedAt = LocalDateTime.now();
    }
    
    public static OrderResponse fromCourseSection(CourseSection cs, Integer oldOrder) {
        return new OrderResponse(cs.getId(), cs.getSection().getId(), oldOrder, cs.getDisplayOrder());
    }
}