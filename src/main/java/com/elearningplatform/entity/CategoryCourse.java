package com.elearningplatform.entity;

import com.elearningplatform.common.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
@Table(name="category_courses",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = "title")
		}
)
public class CategoryCourse extends BaseEntity {
	
	@Column(name="title", unique = true)
	private String title;
	
}
