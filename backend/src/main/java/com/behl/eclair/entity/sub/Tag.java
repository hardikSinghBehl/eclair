package com.behl.eclair.entity.sub;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
public class Tag implements Serializable {

	private static final long serialVersionUID = 2881688793533862766L;

	@Field(name = "name")
	private String name;

}
