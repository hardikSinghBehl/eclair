package com.behl.eclair.dto.response;

import java.time.LocalDateTime;

import com.behl.eclair.entity.sub.Address;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class UserDetailsResponseDto {

	private final String fullName;
	private final String emailId;
	private final Address address;
	private final Integer numberOfArticles;
	private final LocalDateTime createdAt;
	private final LocalDateTime updatedAt;

}
