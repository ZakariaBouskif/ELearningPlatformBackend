package com.elearningplatform.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.elearningplatform.dto.InstructorDto;
import com.elearningplatform.entity.Instructor;
import com.elearningplatform.mapper.InstructorMapper;
import com.elearningplatform.repository.InstructorRepository;
import com.elearningplatform.request.InstructorRequest;
import com.elearningplatform.service.InstructorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {

	private final InstructorRepository repository;
    private final InstructorMapper mapper;
    
    @Override
    public List<InstructorDto> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public InstructorDto findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Category Course not found"));
    }

    @Override
    public InstructorDto create(InstructorRequest request) {
        Instructor entity = mapper.toEntity(request);
        Instructor saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public InstructorDto update(Long id, InstructorRequest request) {
        Instructor entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor not found"));
        mapper.updateEntityFromRequest(request, entity);
        Instructor saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }


}
