package com.behl.eclair.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class TokenRefreshRequestDto {

	private final String refreshToken;

}
