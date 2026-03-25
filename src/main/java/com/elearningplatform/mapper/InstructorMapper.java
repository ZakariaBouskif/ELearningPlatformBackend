package com.elearningplatform.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.elearningplatform.dto.InstructorDto;
import com.elearningplatform.entity.Instructor;
import com.elearningplatform.request.InstructorRequest;


@Mapper(componentModel = "spring", config = BaseMapperConfig.class)
public interface InstructorMapper {
	
	// Entity → DTO
    InstructorDto toDto(Instructor entity);


    // Request → Entity (for CREATE)
    Instructor toEntity(InstructorRequest request);

    // Update existing entity
    void updateEntityFromRequest(InstructorRequest request, @MappingTarget Instructor entity);
}
