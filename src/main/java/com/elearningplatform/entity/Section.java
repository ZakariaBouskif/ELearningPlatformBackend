package com.elearningplatform.entity;



import com.elearningplatform.common.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	@Column(name="display_order")
	private Integer displayOrder = 0;
	
	@Builder.Default
	@Column(name="is_published")
	private Boolean isPublished = false;
	
	@Builder.Default
	@Column(name="is_previewable")
	private Boolean isPreviewable = false;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
	
}
