package com.elearningplatform.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.elearningplatform.dto.StudentDto;
import com.elearningplatform.entity.Student;
import com.elearningplatform.enumeration.ErrorCode;
import com.elearningplatform.exception.BusinessException;
import com.elearningplatform.mapper.StudentMapper;
import com.elearningplatform.repository.StudentRepository;
import com.elearningplatform.request.StudentRequest;
import com.elearningplatform.service.StudentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

	private final StudentRepository repository;
    private final StudentMapper mapper;
    
    @Override
    public List<StudentDto> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public StudentDto findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new BusinessException(ErrorCode.STUDENT_NOT_FOUND));
    }

    @Override
    public StudentDto create(StudentRequest request) {
        Student entity = mapper.toEntity(request);
        Student saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public StudentDto update(Long id, StudentRequest request) {
        Student entity = repository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.STUDENT_NOT_FOUND));
        mapper.updateEntityFromRequest(request, entity);
        Student saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }


}
