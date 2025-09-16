package com.elearningplatform.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.elearningplatform.dto.CategoryCourseDto;
import com.elearningplatform.entity.CategoryCourse;
import com.elearningplatform.request.CategoryCourseRequest;

@Mapper(componentModel = "spring", config = BaseMapperConfig.class)
public interface CategoryCourseMapper {
	
	// Entity → DTO
    CategoryCourseDto toDto(CategoryCourse entity);

    // Request → Entity (for CREATE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "title", source = "title")
    CategoryCourse toEntity(CategoryCourseRequest request);

    // Update existing entity
    void updateEntityFromRequest(CategoryCourseRequest request,
                                 @MappingTarget CategoryCourse entity);

	
}
