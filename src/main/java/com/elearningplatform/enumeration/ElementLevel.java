package com.elearningplatform.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ElementLevel {
	BEGINNER, INTERMEDIATE, ADVANCED;
	
	
	@JsonCreator
    public static ElementLevel fromString(String value) {
        return value == null ? null : ElementLevel.valueOf(value.trim().toUpperCase());
    }
}
