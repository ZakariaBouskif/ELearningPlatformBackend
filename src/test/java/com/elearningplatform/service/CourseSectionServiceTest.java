package com.elearningplatform.service;

import static org.mockito.Mockito.*;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.elearningplatform.entity.Course;
import com.elearningplatform.entity.Instructor;
import com.elearningplatform.entity.Section;
import com.elearningplatform.repository.CourseRepository;
import com.elearningplatform.repository.CourseSectionRepository;
import com.elearningplatform.repository.SectionRepository;
import com.elearningplatform.util.TestDataBuilder;

@ExtendWith(MockitoExtension.class)
@DisplayName("Course section service Unit Tests")
class CourseSectionServiceTest {
	
	@Mock
	private CourseSectionRepository courseSectionRepository;
	
	@Mock
	private CourseRepository courseRepository;
	
	@Mock
	private SectionRepository sectionRepository;
	
	@Mock
	private OrderValidationService orderValidationService;
	
	@InjectMocks
	private CourseSectionService courseSectionService;
	
	private Course testCourse;
	private Section testSection;
	private Instructor testInstructor;
	
	@BeforeEach
	void setUp() {
		testInstructor  = TestDataBuilder.defaultInstructor().build();
		testCourse = TestDataBuilder.defaultcourse().instructors(List.of(testInstructor)).build();
		testCourse.setId(1L);
		
		testSection = TestDataBuilder.defaultSection().build();
		testSection.setId(1L);
	}
	
	@Test
	@DisplayName("Should assign section to course successfully")
	void shouldAssignSectionToCourse() {
		// Given
	    when(courseRepository.findById(1L)).thenReturn(Optional.of(testCourse));
		when(sectionRepository.findById(1L)).thenReturn(Optional.of(testSection));
		when(courseSectionRepository.existsByCourseIdAndSectionId(1L, 1L)).thenReturn(false);
		
		
	}
	

}
