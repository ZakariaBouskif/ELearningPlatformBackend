package com.elearningplatform.service;

import java.util.List;

import com.elearningplatform.dto.CategoryCourseDto;
import com.elearningplatform.request.CategoryCourseRequest;

public interface CategoryCourseService {
	List<CategoryCourseDto> findAll();

	CategoryCourseDto findById(Long id);

	CategoryCourseDto create(CategoryCourseRequest request);

	CategoryCourseDto update(Long id, CategoryCourseRequest request);

	void delete(Long id);
}
