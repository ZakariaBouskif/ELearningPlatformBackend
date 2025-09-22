package com.elearningplatform.service;

import java.util.List;

import com.elearningplatform.dto.SectionDto;
import com.elearningplatform.request.SectionRequest;

public interface SectionService {

	List<SectionDto> findAll();

	SectionDto findById(Long id);

	SectionDto create(SectionRequest request);

	SectionDto update(Long id, SectionRequest request);

	void delete(Long id);
}
