package com.elearningplatform.entity;

import java.util.List;
import java.util.stream.Collectors;

import com.elearningplatform.common.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "sections")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Section extends BaseEntity {

	@Column(nullable = false)
	private String title;

	@Column(columnDefinition = "TEXT")
	private String description;

	@Builder.Default
	@Column(name = "is_published")
	private Boolean isPublished = false;

	@Builder.Default
	@Column(name = "is_previewable")
	private Boolean isPreviewable = false;

	@Builder.Default
	@Column(name = "is_shareable")
	private Boolean isShareable = true;

	@OneToMany(mappedBy = "section", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CourseSection> courseSections;
	
	@OneToMany(mappedBy = "section", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("displayOrder ASC")
    private List<Lesson> lessons;
	
	public List<Course> getCourses(){
		return courseSections.stream()
							.map(CourseSection::getCourse)
							.collect(Collectors.toList());
	}
}
