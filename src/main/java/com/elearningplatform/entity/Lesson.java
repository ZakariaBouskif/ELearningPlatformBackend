package com.elearningplatform.entity;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.elearningplatform.common.BaseEntity;
import com.elearningplatform.enumeration.LessonType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "lessons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Lesson extends BaseEntity {

	@Column(nullable = false)
	private String title;

	@Enumerated(EnumType.STRING)
	@Column(name = "lesson_type", nullable = false)
	private LessonType lessonType;

	@JdbcTypeCode(SqlTypes.JSON)
	@Column(columnDefinition = "jsonb")
	private String content;

	@Builder.Default
	private Integer duration = 0;

	@Builder.Default
	private Integer displayOrder = 0;

	@Builder.Default
	@Column(name = "is_published")
	private Boolean isPublished = false;

	@Builder.Default
	@Column(name = "is_previewable")
	private Boolean isPreviewable = false;

	@Builder.Default
	@Column(name = "is_free")
	private Boolean isFree = false;
	
	@ManyToOne
	@JoinColumn(name="section_id")
	private Section section;
	
	@Transient
	public String getFormattedDuration() {
		if (duration == null)
			return "0 min";
		int minutes = duration / 60;
		return minutes + " min";
	}

	@Transient
	public boolean isVideoLesson() {
		return lessonType == LessonType.VIDEO;
	}

	@Transient
	public boolean isQuizLesson() {
		return lessonType == LessonType.QUIZ;
	}

}
