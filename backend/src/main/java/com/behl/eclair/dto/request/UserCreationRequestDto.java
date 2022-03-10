package com.behl.eclair.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class UserCreationRequestDto {

	@NotBlank
	@Size(max = 100)
	@Schema(example = "Hardik Singh Behl")
	private final String fullName;

	@NotBlank
	@Email
	@Schema(example = "behl.hardiksingh@gmail.com")
	private final String emailId;

	@NotBlank
	@Schema(example = "idk")
	private final String password;

}