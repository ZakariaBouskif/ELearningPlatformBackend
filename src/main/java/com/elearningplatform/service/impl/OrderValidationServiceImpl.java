package com.elearningplatform.service.impl;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.elearningplatform.repository.CourseSectionRepository;
import com.elearningplatform.service.OrderValidationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderValidationServiceImpl implements OrderValidationService {

	private final CourseSectionRepository courseSectionRepository;

	@Override
	public void validateSectionOrder(Long courseId, Integer order) {
		if (order < 0) {
			throw new IllegalArgumentException("Order cannot be negative");
		}

		// Check if order is within reasonable bounds
		Integer sectionCount = courseSectionRepository.countByCourseId(courseId);
		if (order > sectionCount + 100) {
			throw new IllegalArgumentException("Order value too high");
		}

	}

	@Override
	public void validateNoOrderConflicts(Long courseId, Map<Long, Integer> newOrders) {
		Set<Integer> orders = new HashSet<>();

		for (Integer order : newOrders.values()) {
			if (!orders.add(order)) {
				throw new IllegalArgumentException("Duplicate order value: " + order);
			}
		}

	}

	@Override
	public void validateSectionAssignment(Long courseId, Long sectionId) {
		if (courseSectionRepository.existsByCourseIdAndSectionId(courseId, sectionId)) {
			throw new IllegalStateException("Section already assigned to course");
		}
	}

}
