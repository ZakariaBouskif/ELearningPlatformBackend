package com.elearningplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elearningplatform.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
