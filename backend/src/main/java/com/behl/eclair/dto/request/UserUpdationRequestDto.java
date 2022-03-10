package com.behl.eclair.dto.request;

import com.behl.eclair.entity.sub.Address;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class UserUpdationRequestDto {

	@Schema(example = "Hardik Singh Behl")
	private String fullName;

	private Address address;

}
