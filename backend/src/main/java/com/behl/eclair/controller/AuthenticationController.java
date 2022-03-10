package com.behl.eclair.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.behl.eclair.dto.request.TokenRefreshRequestDto;
import com.behl.eclair.dto.request.UserLoginRequestDto;
import com.behl.eclair.dto.response.TokenResponseDto;
import com.behl.eclair.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AuthenticationController {

	private final UserService userService;

	@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "user logged-in successfully"),
			@ApiResponse(responseCode = "401", description = "invalid login credentials provided") })
	@ResponseStatus(value = HttpStatus.OK)
	@Operation(summary = "returns token that represents logged-in user")
	public ResponseEntity<TokenResponseDto> loginHandler(
			@Valid @RequestBody(required = true) final UserLoginRequestDto userLoginRequestDto) {
		return ResponseEntity.ok(userService.login(userLoginRequestDto));
	}

	@PutMapping(value = "/token", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "new access-token generated"),
			@ApiResponse(responseCode = "403", description = "refresh-token has expired") })
	@ResponseStatus(value = HttpStatus.OK)
	@Operation(summary = "generates a new access_token from refresh token")
	public ResponseEntity<TokenResponseDto> accessTokenRefresherHandler(
			@RequestBody(required = true) final TokenRefreshRequestDto tokenRefreshRequestDto) {
		return ResponseEntity.ok(userService.refreshToken(tokenRefreshRequestDto));
	}

}
