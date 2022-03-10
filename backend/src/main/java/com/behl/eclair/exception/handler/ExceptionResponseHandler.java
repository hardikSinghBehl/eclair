package com.behl.eclair.exception.handler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.swagger.v3.oas.annotations.responses.ApiResponse;

@ControllerAdvice
public class ExceptionResponseHandler extends ResponseEntityExceptionHandler {

	@ApiResponse(responseCode = "400", description = "Bad Request")
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException exception,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		BindingResult result = exception.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();

		final var response = new HashMap<>();
		response.put("status", "Failure");
		response.put("message",
				fieldErrors.stream().map(fieldError -> fieldError.getDefaultMessage()).collect(Collectors.toList()));
		response.put("timestamp",
				LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toString());
		return ResponseEntity.badRequest().body(response.toString());
	}

}
