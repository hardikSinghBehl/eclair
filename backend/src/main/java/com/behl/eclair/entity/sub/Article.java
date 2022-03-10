package com.behl.eclair.entity.sub;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
public class Article implements Serializable {

	private static final long serialVersionUID = 7573916966893973487L;

	@Field(name = "title")
	private String title;

	@Field(name = "description")
	private String description;

	@Field(name = "tags")
	private List<Tag> tags;

	@Field(name = "created_at")
	private LocalDateTime createdAt;

	@Field(name = "updated_at")
	private LocalDateTime updatedAt;

}
