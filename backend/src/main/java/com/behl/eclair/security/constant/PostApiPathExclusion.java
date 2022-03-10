package com.behl.eclair.security.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PostApiPathExclusion {

	USERS_ACCOUNT_CREATION("/users"), LOGIN("/login");

	private final String path;
}