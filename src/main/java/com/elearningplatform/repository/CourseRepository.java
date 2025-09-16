package com.elearningplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elearningplatform.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
