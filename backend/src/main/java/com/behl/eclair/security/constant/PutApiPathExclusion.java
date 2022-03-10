package com.behl.eclair.security.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PutApiPathExclusion {

	REFRESH_ACCESS_TOKEN("/token");

	private final String path;
}