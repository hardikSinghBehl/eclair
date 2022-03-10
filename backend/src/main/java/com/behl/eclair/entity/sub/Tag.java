package com.behl.eclair.entity.sub;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Tag implements Serializable {

	private static final long serialVersionUID = 2881688793533862766L;

	@Field(name = "name")
	private String name;

}