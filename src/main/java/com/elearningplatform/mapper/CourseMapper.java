package com.elearningplatform.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.elearningplatform.dto.CategoryCourseDto;
import com.elearningplatform.dto.CourseDto;
import com.elearningplatform.entity.CategoryCourse;
import com.elearningplatform.entity.Course;
import com.elearningplatform.request.CourseRequest;

@Mapper(componentModel = "spring", config = BaseMapperConfig.class)
public interface CourseMapper {

	// Entity → DTO
    CourseDto toDto(Course entity);

    // Request → Entity (for CREATE)
    Course toEntity(CourseRequest request);

    // Update existing entity
    void updateEntityFromRequest(CourseRequest request, @MappingTarget Course entity);
    
    
    default CategoryCourse map(Long id) {
        if (id == null) return null;
        CategoryCourse category = new CategoryCourse();
        category.setId(id);
        return category;
    }
    
    default CategoryCourseDto map(CategoryCourse category) {
        if (category == null) return null;
        CategoryCourseDto dto = new CategoryCourseDto();
        dto.setId(category.getId());
        dto.setTitle(category.getTitle());
        return dto;
    }
    

}
