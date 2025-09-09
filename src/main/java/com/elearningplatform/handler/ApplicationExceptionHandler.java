package com.elearningplatform.handler;

import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.elearningplatform.enumeration.ErrorCode;
import com.elearningplatform.exception.BusinessException;
import com.elearningplatform.response.ErrorResponse;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class ApplicationExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorResponse> handleBusiness(final BusinessException ex){
		final ErrorResponse body = ErrorResponse.builder()
												.code(ex.getErrorCode().getCode())
												.message(ex.getMessage())
												.build();
		log.info("Business Exception: {}", body);
		log.debug(ex.getMessage(), ex);
		return ResponseEntity.status(ex.getErrorCode().getStatus() != null ? ex.getErrorCode().getStatus() : HttpStatus.BAD_REQUEST).body(body);
	}
	
	@ExceptionHandler(DisabledException.class)
	public ResponseEntity<ErrorResponse> handleBusiness(){
		final ErrorResponse body = ErrorResponse.builder()
												.code(ErrorCode.ERR_USER_DISABLED.getCode())
												.message(ErrorCode.ERR_USER_DISABLED.getDefaultMessage())
												.build();
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
	}
	
    @ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleException(final MethodArgumentNotValidException exp){
		final List<ErrorResponse.ValidationError> errors = new ArrayList<>();
		exp.getBindingResult()
			.getAllErrors()
			.forEach(error -> {
				final String fieldName = ((FieldError) error).getField();
				final String errorCode = error.getDefaultMessage();
				errors.add(ErrorResponse.ValidationError.builder()
														.field(fieldName)
														.code(errorCode)
														.message(errorCode)
														.build());
			});
		final ErrorResponse errorResponse = ErrorResponse.builder()
														.validationErrors(errors)
														.build();
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
    
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleException(final BadCredentialsException ex){
    	log.debug(ex.getMessage(), ex);
    	final ErrorResponse response = ErrorResponse.builder()
    												.message(ErrorCode.BAD_CREDENTIALS.getDefaultMessage())
    												.code(ErrorCode.BAD_CREDENTIALS.getCode())
    												.build();
    	return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(final EntityNotFoundException ex) {
        log.debug(ex.getMessage(), ex);
        final ErrorResponse errorResponse = ErrorResponse.builder()
                                                         .code("TBD")
                                                         .message(ex.getMessage())
                                                         .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(final UsernameNotFoundException ex){
    	log.debug(ex.getMessage(), ex);
    	final ErrorResponse response = ErrorResponse.builder()
    												.code(ErrorCode.USERNAME_NOT_FOUND.getCode())
    												.message(ErrorCode.USERNAME_NOT_FOUND.getDefaultMessage())
    												.build();
    	return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ErrorResponse> handleException(final AuthorizationDeniedException ex){
    	log.debug(ex.getMessage(), ex);
    	final ErrorResponse response = ErrorResponse.builder()
    												.message("You are not authorized to perform this operation")
    												.build();
    	return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(final Exception exception) {
        log.error(exception.getMessage(), exception);
        final ErrorResponse response = ErrorResponse.builder()
                                                    .code(ErrorCode.INTERNAL_EXCEPTION.getCode())
                                                    .message(ErrorCode.INTERNAL_EXCEPTION.getDefaultMessage())
                                                    .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
