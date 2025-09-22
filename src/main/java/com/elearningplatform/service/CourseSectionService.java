package com.elearningplatform.service;

import java.util.List;
import java.util.Map;

import com.elearningplatform.entity.Course;
import com.elearningplatform.entity.CourseSection;
import com.elearningplatform.entity.Section;

public interface CourseSectionService {
	CourseSection assignSectionToCourse(Long courseId, Long sectionId, Integer order);
	void removeSectionFromCourse(Long courseId, Long sectionId);
	CourseSection reorderSection(Long courseSectionId, Integer newOrder);
	List<CourseSection> bulkReorderSections(Long courseId, Map<Long, Integer> newOrders);
	void normalizeOrders(Long courseId);
	void moveSectionToPosition(Long courseId, Long sectionId, Integer newPosition);
	void swapSections(Long courseId, Long firstSectionId, Long secondSectionId);
	List<Section> getCourseSections(Long courseId);
	List<Course> getSectionCourses(Long sectionId);
	List<CourseSection> findByCourseIdOrderByDisplayOrder(Long courseId);
}
