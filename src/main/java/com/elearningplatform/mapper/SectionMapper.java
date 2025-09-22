package com.elearningplatform.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.elearningplatform.dto.SectionDto;
import com.elearningplatform.entity.Section;
import com.elearningplatform.request.SectionRequest;

@Mapper(componentModel = "spring", config = BaseMapperConfig.class)
public interface SectionMapper {
	
	// Entity → DTO
    SectionDto toDto(Section entity);

    // Request → Entity (for CREATE)
    Section toEntity(SectionRequest request);

    // Update existing entity
    void updateEntityFromRequest(SectionRequest request, @MappingTarget Section entity);
}
