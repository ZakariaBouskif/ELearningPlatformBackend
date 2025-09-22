package com.elearningplatform.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.elearningplatform.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
	@Query("SELECT c FROM Course c JOIN FETCH c.courseSections cs JOIN FETCH cs.section WHERE c.id = :courseId")
	Optional<Course> findWithSections(@Param("courseId") Long courseId);

	@EntityGraph(attributePaths = { "courseSections", "courseSections.section" })
	Optional<Course> findWithSectionsAndLessonsById(Long courseId);
}
