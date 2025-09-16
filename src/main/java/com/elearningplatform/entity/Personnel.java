package com.elearningplatform.entity;

import java.time.LocalDate;

import com.elearningplatform.common.BaseEntity;
import com.elearningplatform.enumeration.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@MappedSuperclass
public abstract class Personnel extends BaseEntity {
	
	private String firstName;
	
	private String lastName;
	
	@Enumerated(EnumType.ORDINAL)
	private Gender gender;
	
	private LocalDate dateOfBirth;
	
	private String phone;
	
	@Embedded
	private AddressInfo address;
	
	private String profilePictureUrl;
	 
    @Column(length = 1000)
    private String bio;
}
