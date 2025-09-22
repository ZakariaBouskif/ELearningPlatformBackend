package com.elearningplatform.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elearningplatform.dto.CourseDto;
import com.elearningplatform.dto.CourseSectionDto;
import com.elearningplatform.dto.InstructorDto;
import com.elearningplatform.dto.SectionDto;
import com.elearningplatform.entity.CourseSection;
import com.elearningplatform.request.AssignInstructorRequest;
import com.elearningplatform.request.AssignSectionRequest;
import com.elearningplatform.request.CourseRequest;
import com.elearningplatform.request.ReorderRequest;
import com.elearningplatform.request.SectionRequest;
import com.elearningplatform.response.OrderResponse;
import com.elearningplatform.service.CourseSectionService;
import com.elearningplatform.service.CourseService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/courses")
@Tag(name = "Course", description = "Course API")
public class CourseController {

	private final CourseService service;
	private final CourseSectionService courseSectionService;

	public CourseController(CourseService service, CourseSectionService courseSectionService) {
		this.service = service;
		this.courseSectionService = courseSectionService;
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
	public ResponseEntity<Boolean> assignInstructor(@PathVariable Long courseId,
			@RequestBody @Valid AssignInstructorRequest request) {
		boolean hasAssigned = service.assignInstructor(courseId, request);
		return ResponseEntity.ok(hasAssigned);
	}

	@DeleteMapping("/{courseId}/instructors")
	public ResponseEntity<Boolean> unassignInstructor(@PathVariable Long courseId,
			@RequestBody @Valid AssignInstructorRequest request) {
		boolean unassigned = service.unassignInstructor(courseId, request);
		return ResponseEntity.ok(unassigned);
	}

	@GetMapping("/{courseId}/instructors")
	public ResponseEntity<List<InstructorDto>> getInstructors(@PathVariable(required = true) Long courseId) {
		return ResponseEntity.ok(service.getInstructorsByCourse(courseId));
	}

	@PostMapping("/sections/assign")
	public ResponseEntity<CourseSectionDto> assignSectionToCourse(@RequestBody AssignSectionRequest request) {

		CourseSection courseSection = courseSectionService.assignSectionToCourse(request.courseId(),
				request.sectionId(), request.order());

		return ResponseEntity.status(HttpStatus.CREATED).body(CourseSectionDto.fromEntity(courseSection));
	}

	@DeleteMapping("/{courseId}/sections/{sectionId}")
	public ResponseEntity<Void> removeSectionFromCourse(@PathVariable Long courseId, @PathVariable Long sectionId) {
		courseSectionService.removeSectionFromCourse(courseId, sectionId);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/sections/reorder")
	public ResponseEntity<OrderResponse> reorderSection(@RequestBody ReorderRequest request) {
		CourseSection courseSection = courseSectionService.reorderSection(request.courseSectionId(),
				request.newOrder());
		// we need to get the Old order from the entity or track it differently
		OrderResponse response = new OrderResponse(courseSection.getId(), courseSection.getSection().getId(), null,
				courseSection.getDisplayOrder());
		return ResponseEntity.ok(response);
	}

	@PostMapping("/{courseId}/normalize")
	public ResponseEntity<Void> normalizeOrders(@PathVariable Long courseId) {
		courseSectionService.normalizeOrders(courseId);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/{courseId}/sections/{sectionId}/move-to/{newPosition}")
	public ResponseEntity<OrderResponse> moveSectionToPosition(@PathVariable Long courseId,
			@PathVariable Long sectionId, @PathVariable Integer newPosition) {

		courseSectionService.moveSectionToPosition(courseId, sectionId, newPosition);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/{courseId}/sections/swap")
	public ResponseEntity<Void> swapSections(@PathVariable Long courseId, @RequestParam Long firstSectionId,
			@RequestParam Long secondSectionId) {

		courseSectionService.swapSections(courseId, firstSectionId, secondSectionId);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{courseId}/sections")
	public ResponseEntity<List<CourseSectionDto>> getCourseSections(@PathVariable Long courseId) {

		List<CourseSection> courseSections = courseSectionService.findByCourseIdOrderByDisplayOrder(courseId);
		List<CourseSectionDto> dtos = courseSections.stream().map(CourseSectionDto::fromEntity)
				.collect(Collectors.toList());

		return ResponseEntity.ok(dtos);
	}

}
