package com.elearningplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elearningplatform.entity.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Long>{

}
