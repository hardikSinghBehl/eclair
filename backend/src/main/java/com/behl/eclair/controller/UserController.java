package com.behl.eclair.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.behl.eclair.dto.request.UserCreationRequestDto;
import com.behl.eclair.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/users")
public class UserController {

	private final UserService userService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "user account created successfully"),
			@ApiResponse(responseCode = "409", description = "User account already exists"),
			@ApiResponse(responseCode = "423", description = "Possible SQL Injection attack detected") })
	@ResponseStatus(value = HttpStatus.CREATED)
	@Operation(summary = "creates a user account")
	public ResponseEntity<?> userCreationHandler(
			@Valid @RequestBody(required = true) final UserCreationRequestDto userCreationRequestDto) {
		userService.create(userCreationRequestDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

}
