package com.elearningplatform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elearningplatform.entity.CourseInstructor;

public interface CourseInstructorRepository extends JpaRepository<CourseInstructor, Long>{
    List<CourseInstructor> findByCourseId(Long courseId);

}
