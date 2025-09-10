package com.elearningplatform.entity;

import com.elearningplatform.common.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "course_instructors")
public class CourseInstructor extends BaseEntity{

	@ManyToOne
	@JoinColumn(name = "course_id", nullable = false)
	private Course course;

	@ManyToOne
	@JoinColumn(name = "instructor_id", nullable = false)
	private Instructor instructor;
	
	@Column(name="primary_instructor")
    private Boolean primaryInstructor;
	
	@Column(name="order_index")
	private Integer orderIndex;
}
