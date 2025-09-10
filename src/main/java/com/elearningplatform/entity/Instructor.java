package com.elearningplatform.entity;

import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "instructors")
public class Instructor extends Personnel {
	
	private String expertise;      
    private String headline;        
    private String websiteUrl;
    private String linkedinUrl;
    private String youtubeUrl;
    
    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL)
    private List<CourseInstructor> courseAssignments;
}
