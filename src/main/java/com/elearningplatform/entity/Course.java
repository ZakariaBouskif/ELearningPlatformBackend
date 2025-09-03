package com.elearningplatform.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.elearningplatform.enumeration.ElementLevel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="courses")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="title", length = 50,  nullable = false)
	private String title;
	
	@Column(name="subtitle", length = 50)
	private String subtitle;
	
	@Column(name="description", length = 1000)
	private String description;
	
	@ManyToOne
	@JoinColumn(name="category_course_id")
	private CategoryCourse categoryCourse;
	
	@Column(name="language", length = 2) // store like AR/FR/EN/...
	private String language;
	
	@Enumerated(EnumType.STRING)
	@Column(name="course_level")
	private ElementLevel courseLevel;
	
	
	// Media
	@Column(name="thumbnail_url")
	private String thumbnailUrl;
	
	@Column(name="promo_video_url")
	private String promoVideoUrl;
	
	// Pricing
	@Column(name="price", precision = 10, scale = 2)
	private BigDecimal price = BigDecimal.ZERO;
	
	@Column(name = "published_at")
	private LocalDateTime publishedAt;
	
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
	private List<CourseInstructor> instructors;
	
	@CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

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

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public CategoryCourse getCategoryCourse() {
		return categoryCourse;
	}

	public void setCategoryCourse(CategoryCourse categoryCourse) {
		this.categoryCourse = categoryCourse;
	}

	public ElementLevel getCourseLevel() {
		return courseLevel;
	}

	public void setCourseLevel(ElementLevel courseLevel) {
		this.courseLevel = courseLevel;
	}

	public List<CourseInstructor> getInstructors() {
		return instructors;
	}

	public void setInstructors(List<CourseInstructor> instructors) {
		this.instructors = instructors;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getPromoVideoUrl() {
		return promoVideoUrl;
	}

	public void setPromoVideoUrl(String promoVideoUrl) {
		this.promoVideoUrl = promoVideoUrl;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public LocalDateTime getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(LocalDateTime publishedAt) {
		this.publishedAt = publishedAt;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}	
}
