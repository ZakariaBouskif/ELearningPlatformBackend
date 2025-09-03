package com.elearningplatform.entity;

import java.util.List;

import com.elearningplatform.enumeration.ElementLevel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "paths")
public class Path {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="title")
	private String title;
	
	@Column(length = 1000)
	private String description;
	private String category;
	
	private ElementLevel level;
	private String thumbnailUrl;
	private Boolean published = false;
	
	@OneToMany(mappedBy = "path", cascade = CascadeType.ALL)
    List<PathCourse> pathCourses;

    @OneToMany(mappedBy = "path", cascade = CascadeType.ALL)
    private List<PathEnrollment> enrollments;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public ElementLevel getLevel() {
		return level;
	}

	public void setLevel(ElementLevel level) {
		this.level = level;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public Boolean getPublished() {
		return published;
	}

	public void setPublished(Boolean published) {
		this.published = published;
	}

	public List<PathCourse> getPathCourses() {
		return pathCourses;
	}

	public void setPathCourses(List<PathCourse> pathCourses) {
		this.pathCourses = pathCourses;
	}

	public List<PathEnrollment> getEnrollments() {
		return enrollments;
	}

	public void setEnrollments(List<PathEnrollment> enrollments) {
		this.enrollments = enrollments;
	}
   
}
