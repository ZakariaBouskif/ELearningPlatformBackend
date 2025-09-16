package com.elearningplatform.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elearningplatform.dto.InstructorDto;
import com.elearningplatform.request.InstructorRequest;
import com.elearningplatform.service.InstructorService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/instructors")
@Tag(name = "Instructor", description = "Instructor API")
public class InstructorController {
	private final InstructorService service;

	public InstructorController(InstructorService service) {
		this.service = service;
	}

	@GetMapping
	public List<InstructorDto> findAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public InstructorDto findById(@PathVariable Long id) {
		return service.findById(id);
	}

	@PostMapping
	public InstructorDto create(@Valid @RequestBody InstructorRequest request) {
		return service.create(request);
	}

	@PutMapping("/{id}")
	public InstructorDto update(@PathVariable Long id, @Valid @RequestBody InstructorRequest request) {
		return service.update(id, request);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
}
