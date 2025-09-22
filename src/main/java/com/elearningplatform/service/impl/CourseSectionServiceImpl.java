package com.elearningplatform.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.elearningplatform.entity.Course;
import com.elearningplatform.entity.CourseSection;
import com.elearningplatform.entity.Section;
import com.elearningplatform.enumeration.ErrorCode;
import com.elearningplatform.exception.BusinessException;
import com.elearningplatform.repository.CourseRepository;
import com.elearningplatform.repository.CourseSectionRepository;
import com.elearningplatform.repository.SectionRepository;
import com.elearningplatform.service.CourseSectionService;
import com.elearningplatform.service.OrderValidationService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class CourseSectionServiceImpl implements CourseSectionService {
	
	/**
	 * 	Complete Reordering Algorithm
	  	Initial: [A(0), B(1), C(2), D(3), E(4)]
		Move C from position 2 to position 0
		
		Step 1: Increment orders 0-1 (A and B move down)
		        [A(0)→A(1), B(1)→B(2), C(2), D(3), E(4)]
		
		Step 2: Set C to new order 0
		        [A(1), B(2), C(0), D(3), E(4)]
		
		Step 3: Normalize to consecutive ordering
		        [C(0), A(1), B(2), D(3), E(4)]
	 */
	private final CourseSectionRepository courseSectionRepository;
	private final CourseRepository courseRepository;
	private final SectionRepository sectionRepository;
	private final OrderValidationService orderValidationService;

	@Transactional
	@Override
	public CourseSection assignSectionToCourse(Long courseId, Long sectionId, Integer order) {
		// Check if already assigned
		if (courseSectionRepository.existsByCourseIdAndSectionId(courseId, sectionId)) {
			throw new BusinessException(ErrorCode.SECTION_ALREADY_ASSIGNED_TO_COURSE);
		}
		Course course = courseRepository.findById(courseId)
				.orElseThrow(() -> new BusinessException(ErrorCode.COURSE_NOT_FOUND));
		Section section = sectionRepository.findById(sectionId)
				.orElseThrow(() -> new BusinessException(ErrorCode.SECTION_NOT_FOUND));

		// Determine the order position
		Integer displayOrder = order != null ? order : getNextAvailableOrder(courseId);
		CourseSection courseSection = CourseSection.builder().course(course).section(section).displayOrder(displayOrder)
				.build();
		return courseSectionRepository.save(courseSection);
	}

	@Transactional
	@Override
	public void removeSectionFromCourse(Long courseId, Long sectionId) {
		CourseSection courseSection = courseSectionRepository.findByCourseIdAndSectionId(courseId, sectionId)
				.orElseThrow(() -> new BusinessException(ErrorCode.SECTION_NOT_ASSIGNED_TO_COURSE));
		Integer removedOrder = courseSection.getDisplayOrder();

		// Remove the assignement
		courseSectionRepository.delete(courseSection);

		// Reorder remaining sections
		reorderAfterRemoval(courseId, removedOrder);
	}

	@Transactional
	@Override
	public CourseSection reorderSection(Long courseSectionId, Integer newOrder) {
		CourseSection courseSection = courseSectionRepository.findById(courseSectionId)
				.orElseThrow(() -> new EntityNotFoundException("Course section not found"));

		if (!courseSection.getCourse().getId().equals(courseSection.getCourse().getId())) {
			throw new IllegalArgumentException("Cannot reorder across different courses");
		}
		Integer oldOrder = courseSection.getDisplayOrder();
		if(oldOrder.equals(newOrder)) {
			return courseSection;
		}
		orderValidationService.validateSectionOrder(courseSection.getCourse().getId(), newOrder);
		
		// Perform the reordering
		if(oldOrder < newOrder) {
			// moving down - decrement orders between old and new position
			courseSectionRepository.decrementOrdersBetween(
					courseSection.getCourse().getId(),
					newOrder,
					oldOrder - 1,
					courseSectionId
					);
		} else {
			// moving up - increment orders between new and old position
			courseSectionRepository.incrementOrdersBetween(
						courseSection.getCourse().getId(),
						newOrder,
						oldOrder - 1,
						courseSectionId
					);
		}
		
		// update the moved section
		courseSectionRepository.updateOrder(courseSectionId, newOrder);
		
		// refresh and return
		return courseSectionRepository.findById(courseSectionId).get();
	}

	@Transactional
	@Override
	public List<CourseSection> bulkReorderSections(Long courseId, Map<Long, Integer> newOrders) {
		
		List<CourseSection> updatedSections = new ArrayList<>();
		for (Map.Entry<Long, Integer> entry : newOrders.entrySet()) {
			CourseSection courseSection = reorderSection(entry.getKey(), entry.getValue());
			updatedSections.add(courseSection);
		}
		// normalize orders to remove gaps
		normalizeOrders(courseId);
		
		return updatedSections;
	}

	@Transactional
	@Override
	public void normalizeOrders(Long courseId) {
		List<CourseSection> courseSections = courseSectionRepository.findByCourseIdOrderByDisplayOrder(courseId);
		for(int i = 0; i < courseSections.size(); i++) {
			CourseSection coursesection = courseSections.get(i);
			if(!coursesection.getDisplayOrder().equals(i)) {
				coursesection.setDisplayOrder(i);
				courseSectionRepository.save(coursesection);
			}
		}

	}

	@Transactional
	@Override
	public void moveSectionToPosition(Long courseId, Long sectionId, Integer newPosition) {
	    CourseSection courseSection = courseSectionRepository.findByCourseIdAndSectionId(courseId, sectionId)
	            .orElseThrow(() -> new EntityNotFoundException("Section not found in course"));
	        
        reorderSection(courseSection.getId(), newPosition);
	}

    @Transactional
	@Override
	public void swapSections(Long courseId, Long firstSectionId, Long secondSectionId) {
		CourseSection first = courseSectionRepository.findByCourseIdAndSectionId(courseId, firstSectionId)
		            .orElseThrow(() -> new EntityNotFoundException("First section not found"));
		        
		CourseSection second = courseSectionRepository.findByCourseIdAndSectionId(courseId, secondSectionId)
		    .orElseThrow(() -> new EntityNotFoundException("Second section not found"));
		
		Integer firstOrder = first.getDisplayOrder();
		Integer secondOrder = second.getDisplayOrder();
		
		// Swap orders
		courseSectionRepository.updateOrder(first.getId(), secondOrder);
		courseSectionRepository.updateOrder(second.getId(), firstOrder);

	}

	@Override
	public List<Section> getCourseSections(Long courseId) {
		return courseSectionRepository.findByCourseIdOrdered(courseId).stream()
	            .map(CourseSection::getSection)
	            .collect(Collectors.toList());
	}

	@Override
	public List<Course> getSectionCourses(Long sectionId) {
		  return courseSectionRepository.findBySectionIdOrderByDisplayOrder(sectionId).stream()
		            .map(CourseSection::getCourse)
		            .collect(Collectors.toList());
	}

	private Integer getNextAvailableOrder(Long courseId) {
		Integer maxOrder = courseSectionRepository.findMaxOrderByCourseId(courseId);
		return maxOrder != null ? maxOrder + 1 : 0;
	}

	private void reorderAfterRemoval(Long courseId, Integer removedOrder) {
		 // Decrement orders of all sections that were after the removed one
        courseSectionRepository.decrementOrdersAfter(courseId, removedOrder);
        
        // Normalize to ensure consecutive ordering
        normalizeOrders(courseId);
	}

	@Override
	public List<CourseSection> findByCourseIdOrderByDisplayOrder(Long courseId) {
		return courseSectionRepository.findByCourseIdOrderByDisplayOrder(courseId);
	}
}
