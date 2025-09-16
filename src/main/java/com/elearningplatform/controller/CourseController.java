package com.elearningplatform.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elearningplatform.dto.CourseDto;
import com.elearningplatform.dto.InstructorDto;
import com.elearningplatform.request.AssignInstructorRequest;
import com.elearningplatform.request.CourseRequest;
import com.elearningplatform.service.CourseService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1/courses")
@Tag(name = "Course", description = "Course API")
public class CourseController {

	private final CourseService service;

	public CourseController(CourseService service) {
		this.service = service;
	}

	@GetMapping
	public List<CourseDto> findAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public CourseDto findById(@PathVariable Long id) {
		return service.findById(id);
	}

	@PostMapping
	public CourseDto create(@Valid @RequestBody CourseRequest request) {
		return service.create(request);
	}

	@PutMapping("/{id}")
	public CourseDto update(@PathVariable Long id, @Valid @RequestBody CourseRequest request) {
		return service.update(id, request);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

	@PostMapping("/{courseId}/instructors")
	public ResponseEntity<Boolean> assignInstructor(@PathVariable Long courseId, @RequestBody @Valid AssignInstructorRequest request) {

		boolean hasAssigned = service.assignInstructor(courseId, request);
		return ResponseEntity.ok(hasAssigned);
	}
	
    @GetMapping("/{courseId}/instructors")
    public ResponseEntity<List<InstructorDto>> getInstructors(@PathVariable(required = true) Long courseId) {
        return ResponseEntity.ok(service.getInstructorsByCourse(courseId));
    }

}
