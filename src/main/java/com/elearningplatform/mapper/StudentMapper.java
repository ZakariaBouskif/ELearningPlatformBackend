package com.elearningplatform.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.elearningplatform.dto.StudentDto;
import com.elearningplatform.entity.Student;
import com.elearningplatform.request.StudentRequest;

@Mapper(componentModel = "spring", config = BaseMapperConfig.class)
public interface StudentMapper {

	// Entity → DTO
    StudentDto toDto(Student entity);

    // Request → Entity (for CREATE)
    @Mapping(target = "id", ignore = true)
    Student toEntity(StudentRequest request);

    // Update existing entity
    void updateEntityFromRequest(StudentRequest request, @MappingTarget Student entity);
}
