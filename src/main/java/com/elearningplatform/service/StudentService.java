package com.elearningplatform.service;

import java.util.List;

import com.elearningplatform.dto.StudentDto;
import com.elearningplatform.request.StudentRequest;

public interface StudentService {

	List<StudentDto> findAll();

	StudentDto findById(Long id);

	StudentDto create(StudentRequest request);

	StudentDto update(Long id, StudentRequest request);

	void delete(Long id);
}
