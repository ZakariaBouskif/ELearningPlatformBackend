package com.elearningplatform.service;

import java.util.List;

import com.elearningplatform.dto.InstructorDto;
import com.elearningplatform.request.InstructorRequest;

public interface InstructorService {

	List<InstructorDto> findAll();

	InstructorDto findById(Long id);

	InstructorDto create(InstructorRequest request);

	InstructorDto update(Long id, InstructorRequest request);

	void delete(Long id);
}
