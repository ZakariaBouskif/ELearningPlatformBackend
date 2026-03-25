package com.elearningplatform.util;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.elearningplatform.entity.Course;
import com.elearningplatform.entity.CourseSection;
import com.elearningplatform.entity.Instructor;
import com.elearningplatform.entity.Section;
import com.elearningplatform.enumeration.ElementLevel;
import com.elearningplatform.enumeration.Gender;

public class TestDataBuilder {
	
	public static Course.CourseBuilder defaultcourse(){
		return Course.builder()
					.title("Test Course")
					.description("Test Course Description")
					.price(BigDecimal.valueOf(99.99))
					.courseLevel(ElementLevel.BEGINNER);
	}
	
	public static Section.SectionBuilder defaultSection(){
		return Section.builder()
						.title("Test Section")
						.description("Test Section Description")
						.isPublished(true);
	}
	
	public static Instructor.InstructorBuilder defaultInstructor(){
		return Instructor.builder()
						.firstName("f instructor 1")
						.lastName("l instructor 1")
						.gender(Gender.MALE);
	}
	
	public static CourseSection.CourseSectionBuilder defaultCourseSection(){
		return CourseSection.builder()
				.displayOrder(0);
	}
	
	public static List<?> createCourseSections(Course course, Section... sections) {
        return Arrays.stream(sections)
            .map(section -> CourseSection.builder()
                .course(course)
                .section(section)
                .displayOrder(0)
                .build())
            .toList();
    }
}
