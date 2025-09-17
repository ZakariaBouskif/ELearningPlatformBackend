package com.elearningplatform.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.elearningplatform.dto.CourseDto;
import com.elearningplatform.dto.InstructorDto;
import com.elearningplatform.entity.CategoryCourse;
import com.elearningplatform.entity.Course;
import com.elearningplatform.entity.CourseInstructor;
import com.elearningplatform.entity.Instructor;
import com.elearningplatform.enumeration.ErrorCode;
import com.elearningplatform.exception.BusinessException;
import com.elearningplatform.mapper.CourseMapper;
import com.elearningplatform.repository.CategoryCourseRepository;
import com.elearningplatform.repository.CourseInstructorRepository;
import com.elearningplatform.repository.CourseRepository;
import com.elearningplatform.repository.InstructorRepository;
import com.elearningplatform.request.AssignInstructorRequest;
import com.elearningplatform.request.CourseRequest;
import com.elearningplatform.service.CourseService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

	private final CourseRepository courseRepository;
	private final CategoryCourseRepository categoryCourseRepository;
	private final InstructorRepository instructorRepository;
	private final CourseInstructorRepository courseInstructorRepository;

	private final CourseMapper mapper;

	@Override
	public List<CourseDto> findAll() {
		return courseRepository.findAll().stream().map(mapper::toDto).toList();
	}

	@Override
	public CourseDto findById(Long id) {
		return courseRepository.findById(id).map(mapper::toDto)
				.orElseThrow(() -> new BusinessException(ErrorCode.COURSE_ALREADY_EXISTS));
	}

	@Override
	public CourseDto create(CourseRequest request) {
		CategoryCourse categoryCourse = categoryCourseRepository.findById(request.categoryCourse())
				.orElseThrow(() -> new BusinessException(ErrorCode.CATEGORY_NOT_FOUND));
		Course entity = mapper.toEntity(request);
		entity.setCategoryCourse(categoryCourse);
		Course saved = courseRepository.save(entity);
		
		return mapper.toDto(saved);
	}

	@Override
	public CourseDto update(Long id, CourseRequest request) {
		Course entity = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
		CategoryCourse categoryCourse = categoryCourseRepository.findById(request.categoryCourse())
				.orElseThrow(() -> new BusinessException(ErrorCode.CATEGORY_NOT_FOUND));
		mapper.updateEntityFromRequest(request, entity);
		entity.setCategoryCourse(categoryCourse);
		Course saved = courseRepository.save(entity);
		return mapper.toDto(saved);
	}

	@Override
	public void delete(Long id) {
		courseRepository.deleteById(id);
	}

	@Override
	public boolean assignInstructor(Long courseId, AssignInstructorRequest request) {
		Course course = courseRepository.findById(courseId)
				.orElseThrow(() -> new BusinessException(ErrorCode.COURSE_NOT_FOUND));

		Instructor instructor = instructorRepository.findById(request.instructorId())
				.orElseThrow(() -> new BusinessException(ErrorCode.INSTRUCTOR_NOT_FOUND));

		// Check for duplicates
		boolean alreadyAssigned = course.getInstructors().stream()
				.anyMatch(ci -> ci.getInstructor().getId().equals(instructor.getId()));
		if (alreadyAssigned) {
			throw new IllegalStateException("Instructor already assigned to this course");
		}

		CourseInstructor relation = CourseInstructor.builder().course(course).instructor(instructor)
				.primaryInstructor(request.primaryInstructor()).build();

		course.getInstructors().add(relation);
		return courseRepository.save(course).getId() > 0;
	}

	@Override
	public List<InstructorDto> getInstructorsByCourse(Long courseId) {
		return courseInstructorRepository.findByCourseId(courseId).stream().map(ci -> ci.getInstructor())
				.map(instructor -> InstructorDto.builder()
											.id(instructor.getId())
											.firstName(instructor.getFirstName())
											.lastName(instructor.getLastName())
											.build())
				.toList();
	}

}
