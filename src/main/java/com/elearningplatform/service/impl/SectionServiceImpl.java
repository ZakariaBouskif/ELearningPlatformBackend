package com.elearningplatform.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.elearningplatform.dto.SectionDto;
import com.elearningplatform.entity.Section;
import com.elearningplatform.enumeration.ErrorCode;
import com.elearningplatform.exception.BusinessException;
import com.elearningplatform.mapper.SectionMapper;
import com.elearningplatform.repository.SectionRepository;
import com.elearningplatform.request.SectionRequest;
import com.elearningplatform.service.SectionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SectionServiceImpl implements SectionService {

	private final SectionRepository repository;
    private final SectionMapper mapper;
    
    @Override
    public List<SectionDto> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public SectionDto findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new BusinessException(ErrorCode.SECTION_NOT_FOUND));
    }

    @Override
    public SectionDto create(SectionRequest request) {
        Section entity = mapper.toEntity(request);
        Section saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public SectionDto update(Long id, SectionRequest request) {
        Section entity = repository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.SECTION_NOT_FOUND));
        mapper.updateEntityFromRequest(request, entity);
        Section saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }


}
