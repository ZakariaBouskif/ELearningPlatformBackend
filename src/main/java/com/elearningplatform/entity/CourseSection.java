package com.elearningplatform.entity;

import com.elearningplatform.common.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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
@Table(name = "course_sections", uniqueConstraints = @UniqueConstraint(columnNames = { "course_id", "section_id" }))
public class CourseSection extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id", nullable = false)
	private Course course;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "section_id", nullable = false)
	private Section section;

	@Builder.Default
	@Column(name = "display_order", nullable = false)
	private Integer displayOrder = 0;
	
	@PrePersist
	@PreUpdate
	private void validateOrder() {
		if(displayOrder == null) displayOrder = 0;
		displayOrder = Math.max(0, displayOrder);
	}

}
