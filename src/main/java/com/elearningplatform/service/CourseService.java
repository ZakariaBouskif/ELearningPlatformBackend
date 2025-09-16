package com.elearningplatform.service;

import java.util.List;

import com.elearningplatform.dto.CourseDto;
import com.elearningplatform.dto.InstructorDto;
import com.elearningplatform.request.AssignInstructorRequest;
import com.elearningplatform.request.CourseRequest;

public interface CourseService {

	List<CourseDto> findAll();

	CourseDto findById(Long id);

	CourseDto create(CourseRequest request);

	CourseDto update(Long id, CourseRequest request);

	void delete(Long id);
	
	boolean assignInstructor(Long courseId, AssignInstructorRequest request);
	
	List<InstructorDto> getInstructorsByCourse(Long courseId);
}
