package com.elearningplatform.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.elearningplatform.dto.CategoryCourseDto;
import com.elearningplatform.entity.CategoryCourse;
import com.elearningplatform.enumeration.ErrorCode;
import com.elearningplatform.exception.BusinessException;
import com.elearningplatform.mapper.CategoryCourseMapper;
import com.elearningplatform.repository.CategoryCourseRepository;
import com.elearningplatform.request.CategoryCourseRequest;
import com.elearningplatform.service.CategoryCourseService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryCourseServiceImpl implements CategoryCourseService{
	
	private final CategoryCourseRepository repository;
    private final CategoryCourseMapper mapper;

    @Override
    public List<CategoryCourseDto> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public CategoryCourseDto findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new BusinessException(ErrorCode.CATEGORY_NOT_FOUND));
    }

    @Override
    public CategoryCourseDto create(CategoryCourseRequest request) {
        CategoryCourse entity = mapper.toEntity(request);
        CategoryCourse saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public CategoryCourseDto update(Long id, CategoryCourseRequest request) {
        CategoryCourse entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("CategoryCourse not found"));
        mapper.updateEntityFromRequest(request, entity);
        CategoryCourse saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
