package com.elearningplatform.service;

import java.util.Map;

public interface OrderValidationService {
	void validateSectionOrder(Long courseId, Integer order);
	void validateNoOrderConflicts(Long courseId, Map<Long, Integer> newOrders);
	void validateSectionAssignment(Long courseId, Long sectionId);
}
