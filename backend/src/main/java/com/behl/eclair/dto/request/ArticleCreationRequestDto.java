package com.behl.eclair.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class ArticleCreationRequestDto {

	@Schema(example = "How to use MongoDB and Karate with Java Spring-boot")
	private final String title;

	@Schema(example = "https://github.com/hardikSinghBehl/mongodb-crud-spring-security-karate-dsl")
	private final String description;

	@Schema(example = "Java, Spring-boot, Karate")
	private final String tags;

}
