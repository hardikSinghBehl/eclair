package com.behl.eclair.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.behl.eclair.dto.request.UserCreationRequestDto;
import com.behl.eclair.dto.request.UserUpdationRequestDto;
import com.behl.eclair.dto.response.UserDetailsResponseDto;
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
			@ApiResponse(responseCode = "409", description = "User account already exists") })
	@ResponseStatus(value = HttpStatus.CREATED)
	@Operation(summary = "creates a user account")
	public ResponseEntity<?> userCreationHandler(
			@Valid @RequestBody(required = true) final UserCreationRequestDto userCreationRequestDto) {
		userService.create(userCreationRequestDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "user account updated successfully") })
	@ResponseStatus(value = HttpStatus.OK)
	@Operation(summary = "updates account details")
	public ResponseEntity<?> userUpdationHandler(
			@Valid @RequestBody(required = true) final UserUpdationRequestDto userUpdationRequestDto) {
		userService.update(userUpdationRequestDto);
		return ResponseEntity.ok().build();
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	@Operation(summary = "retreives account details")
	public ResponseEntity<UserDetailsResponseDto> userDetailsRetreivalHandler() {
		return ResponseEntity.ok(userService.retreiveDetails());
	}

}
