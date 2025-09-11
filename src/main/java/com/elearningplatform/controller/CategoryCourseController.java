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

import com.elearningplatform.dto.CategoryCourseDto;
import com.elearningplatform.request.CategoryCourseRequest;
import com.elearningplatform.service.CategoryCourseService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/category-courses")
@Tag(name = "Catgory Course", description = "Category courses API")
public class CategoryCourseController {

	private final CategoryCourseService service;

	public CategoryCourseController(CategoryCourseService service) {
		this.service = service;
	}

	@GetMapping
    public List<CategoryCourseDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public CategoryCourseDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public CategoryCourseDto create(@Valid @RequestBody CategoryCourseRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public CategoryCourseDto update(@PathVariable Long id,
                                    @Valid @RequestBody CategoryCourseRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
