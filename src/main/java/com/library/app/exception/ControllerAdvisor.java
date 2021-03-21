package com.library.app.exception;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.library.app.model.error.ErrorResponse;
import com.library.app.model.response.BaseResponse;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<BaseResponse> handleNotFoundException(NotFoundException ex, WebRequest request) {
		return new ResponseEntity<>(new BaseResponse(false, ex.getError().getErrorMessage()), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<BaseResponse> handleServiceException(ServiceException ex, WebRequest request) {
		return new ResponseEntity<>(new BaseResponse(false, ex.getError().getErrorMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(DataConflictException.class)
	public ResponseEntity<BaseResponse> handleDataConflictException(DataConflictException ex, WebRequest request) {
		return new ResponseEntity<>(new BaseResponse(false, ex.getError().getErrorMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ ConstraintViolationException.class, UnexpectedTypeException.class })
	public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex,
			WebRequest request) {
		Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
		StringBuilder sb = new StringBuilder();
		for (ConstraintViolation<?> violation : violations) {
			sb.append(violation.getMessage());
			sb.append("\n");
		}
		sb.deleteCharAt(sb.lastIndexOf("\n"));
		ErrorResponse response = new ErrorResponse(false, sb.toString());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<ObjectError> errors = ex.getBindingResult().getAllErrors();
		StringBuilder sb = new StringBuilder();
		for (ObjectError error : errors) {
			sb.append(error.getDefaultMessage());
			sb.append("\n");
		}
		sb.deleteCharAt(sb.lastIndexOf("\n"));
		ErrorResponse response = new ErrorResponse(false, sb.toString());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

}
