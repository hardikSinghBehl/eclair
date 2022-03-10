package com.behl.eclair.entity.sub;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Field;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Address implements Serializable {

	private static final long serialVersionUID = 2120959227879748705L;

	@Field(name = "address_line_1")
	@Schema(example = "address_line_1")
	private String lineOne;

	@Field(name = "address_line_2")
	@Schema(example = "address_line_2")
	private String lineTwo;

	@Field(name = "land_mark")
	@Schema(example = "landmark")
	private String landMark;

	@Field(name = "pin_code")
	@Schema(example = "120240")
	private Integer pinCode;

}
