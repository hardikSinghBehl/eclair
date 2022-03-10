package com.behl.eclair.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class UserLoginRequestDto {

	@NotBlank
	@Email
	@Schema(example = "behl.hardiksingh@gmail.com")
	private final String emailId;

	@NotBlank
	@Schema(example = "idk")
	private final String password;

}
