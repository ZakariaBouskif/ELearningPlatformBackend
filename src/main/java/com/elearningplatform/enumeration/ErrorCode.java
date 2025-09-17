package com.elearningplatform.enumeration;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {

	EMAIL_ALREADY_EXISTS("ERR_EMAIL_EXISTS", "Email already exists", HttpStatus.CONFLICT),
	PHONE_ALREADY_EXISTS("ERR_PHONE_EXISTS", "An account with this phone number already exists", HttpStatus.CONFLICT),
	PASSWORD_MISMATCH("ERR_PASSWORD_MISMATCH", "The password and confirmation do not match", HttpStatus.BAD_REQUEST),
	CHANGE_PASSWORD_MISMATCH("ERR_PASSWORD_MISMATCH", "New password and confirmation do not match",
			HttpStatus.BAD_REQUEST),
	ERR_SENDING_ACTIVATION_EMAIL("ERR_SENDING_ACTIVATION_EMAIL", "An error occurred while sending the activation email",
			HttpStatus.INTERNAL_SERVER_ERROR),

	ERR_USER_DISABLED("ERR_USER_DISABLED",
			"User account is disabled, please activate your account or contact the administrator",
			HttpStatus.UNAUTHORIZED),
	INVALID_CURRENT_PASSWORD("INVALID_CURRENT_PASSWORD", "The current password is incorrect", HttpStatus.BAD_REQUEST),
	USER_NOT_FOUND("USER_NOT_FOUND", "User not found", HttpStatus.NOT_FOUND),
	ACCOUNT_ALREADY_DEACTIVATED("ACCOUNT_ALREADY_DEACTIVATED", "Account has been deactivated", HttpStatus.BAD_REQUEST),
	BAD_CREDENTIALS("BAD_CREDENTIALS", "Username and / or password is incorrect", HttpStatus.UNAUTHORIZED),
	INTERNAL_EXCEPTION("INTERNAL_EXCEPTION", "An internal exception occurred, please try again or contact the admin",
			HttpStatus.INTERNAL_SERVER_ERROR),
	USERNAME_NOT_FOUND("USERNAME_NOT_FOUND", "Cannot find user with the provided username", HttpStatus.NOT_FOUND),
	
	
	CATEGORY_NOT_FOUND("CATEGORY_NOT_FOUND", "Category not found", HttpStatus.NOT_FOUND),
	CATEGORY_ALREADY_EXISTS("CATEGORY_ALREADY_EXISTS", "Category already exists", HttpStatus.CONFLICT),
	
	
	COURSE_NOT_FOUND("COURSE_NOT_FOUND", "Course not found", HttpStatus.NOT_FOUND),
	INSTRUCTOR_NOT_FOUND("INSTRUCTOR_NOT_FOUND", "Instructor not found", HttpStatus.NOT_FOUND),
	STUDENT_NOT_FOUND("STUDENT_NOT_FOUND", "Student not found", HttpStatus.NOT_FOUND),
	
	COURSE_ALREADY_EXISTS("COURSE_ALREADY_EXISTS", "Course already exists", HttpStatus.CONFLICT),
	INSTRCUTOR_ALREADY_ASSIGNED_TO_COURSE("INSTRUCTOR_ALREADY_ASSIGNED_TO_COURSE", "Instructor already assigned to this course", HttpStatus.CONFLICT),
	
	
	;

	private final String code;
	private final String defaultMessage;
	private final HttpStatus status;

	ErrorCode(final String code, final String defaultMessage, final HttpStatus status) {
		this.code = code;
		this.defaultMessage = defaultMessage;
		this.status = status;
	}
}
