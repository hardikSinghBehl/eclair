package com.behl.eclair.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class TokenResponseDto {

	private final String accessToken;
	private final String refreshToken;

}