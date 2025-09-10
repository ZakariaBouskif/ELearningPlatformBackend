package com.elearningplatform.entity;

import java.time.LocalDateTime;

import com.elearningplatform.common.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Table(name = "enrollments", uniqueConstraints = @UniqueConstraint(columnNames = { "student_id", "course_id" }))
public class Enrollment extends BaseEntity{

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id", nullable = false)
	private Student student;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id", nullable = false)
	private Course course;
	
	@Builder.Default
	@Column(name="enrolled_at")
	private LocalDateTime enrolledAt = LocalDateTime.now();
	
	@Builder.Default
	@Column(name="progress")
	private Double progress = 0.0;
	
	@Column(name="completed_at")
	private LocalDateTime completedAt;
	
	@Builder.Default
	@Column(name="certificate_issued")
	private Boolean certificate_issued = false;
}
