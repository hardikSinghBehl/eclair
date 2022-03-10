package com.behl.eclair.entity.sub;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
public class Address implements Serializable {

	private static final long serialVersionUID = 2120959227879748705L;

	@Field(name = "address_line_1")
	private String lineOne;

	@Field(name = "address_line_2")
	private String lineTwo;

	@Field(name = "land_mark")
	private String landMark;

	@Field(name = "pin_code")
	private Integer pinCode;

}
