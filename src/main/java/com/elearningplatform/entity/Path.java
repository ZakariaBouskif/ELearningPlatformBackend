package com.elearningplatform.entity;

import java.util.List;

import com.elearningplatform.common.BaseEntity;
import com.elearningplatform.enumeration.ElementLevel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "paths")
public class Path extends BaseEntity {

	@Column(name="title")
	private String title;
	
	@Column(length = 1000)
	private String description;
	private String category;
	
	private ElementLevel level;
	private String thumbnailUrl;
	
	@Builder.Default
	private Boolean published = false;
	
	@OneToMany(mappedBy = "path", cascade = CascadeType.ALL)
    List<PathCourse> pathCourses;

    @OneToMany(mappedBy = "path", cascade = CascadeType.ALL)
    private List<PathEnrollment> enrollments;
}
