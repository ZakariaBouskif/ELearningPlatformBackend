package com.elearningplatform.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.elearningplatform.dto.CourseDto;
import com.elearningplatform.dto.CourseFullDto;
import com.elearningplatform.dto.CourseWithSectionsDto;
import com.elearningplatform.dto.InstructorDto;
import com.elearningplatform.dto.SectionDto;
import com.elearningplatform.entity.CategoryCourse;
import com.elearningplatform.entity.Course;
import com.elearningplatform.entity.CourseInstructor;
import com.elearningplatform.entity.CourseSection;
import com.elearningplatform.entity.Instructor;
import com.elearningplatform.entity.Section;
import com.elearningplatform.enumeration.ErrorCode;
import com.elearningplatform.exception.BusinessException;
import com.elearningplatform.mapper.CourseMapper;
import com.elearningplatform.repository.*;
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
    private final CourseSectionRepository courseSectionRepository;

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
			throw new BusinessException(ErrorCode.INSTRCUTOR_ALREADY_ASSIGNED_TO_COURSE);
		}

		CourseInstructor relation = CourseInstructor.builder().course(course).instructor(instructor)
				.primaryInstructor(request.primaryInstructor()).build();

		course.getInstructors().add(relation);
		return courseRepository.save(course).getId() > 0;
	}
	
	@Override
	public boolean unassignInstructor(Long courseId, AssignInstructorRequest request) {
		Course course = courseRepository.findById(courseId)
				.orElseThrow(() -> new BusinessException(ErrorCode.COURSE_NOT_FOUND));

		Instructor instructor = instructorRepository.findById(request.instructorId())
				.orElseThrow(() -> new BusinessException(ErrorCode.INSTRUCTOR_NOT_FOUND));

		 // Find the relation
	    CourseInstructor relation = course.getInstructors().stream()
	            .filter(ci -> ci.getInstructor().getId().equals(instructor.getId()))
	            .findFirst()
	            .orElseThrow(() -> new BusinessException(ErrorCode.INSTRUCTOR_NOT_ASSIGNED_TO_COURSE));

	    course.getInstructors().remove(relation);

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

	@Override
	public CourseWithSectionsDto getCourseWithSections(Long courseId) {
		return null;
	}

	@Override
	public CourseFullDto getFullCourse(Long courseId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SectionDto> getSectionsByCourseId(Long courseId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}


