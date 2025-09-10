package com.elearningplatform.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.elearningplatform.common.BaseEntity;
import com.elearningplatform.enumeration.ElementLevel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="courses")
public class Course extends BaseEntity{
	
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
	@Builder.Default
	@Column(name="price", precision = 10, scale = 2)
	private BigDecimal price = BigDecimal.ZERO;
	
	@Column(name = "published_at")
	private LocalDateTime publishedAt;
	
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
	private List<CourseInstructor> instructors;
}
