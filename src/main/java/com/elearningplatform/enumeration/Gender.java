package com.elearningplatform.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Gender {
	MALE, FEMALE;
	
	
	@JsonCreator
    public static Gender fromString(String value) {
        return value == null ? null : Gender.valueOf(value.trim().toUpperCase());
    }
}
