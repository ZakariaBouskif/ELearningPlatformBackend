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

import com.elearningplatform.dto.StudentDto;
import com.elearningplatform.request.StudentRequest;
import com.elearningplatform.service.StudentService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/students")
@Tag(name = "Student", description = "Student API")
public class StudentController {
	
	private final StudentService service;
	
	public StudentController(StudentService service){
		this.service = service;
	}
	
	@GetMapping
    public List<StudentDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public StudentDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public StudentDto create(@Valid @RequestBody StudentRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public StudentDto update(@PathVariable Long id,
                                    @Valid @RequestBody StudentRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
