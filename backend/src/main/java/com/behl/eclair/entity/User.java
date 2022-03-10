package com.behl.eclair.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.behl.eclair.entity.sub.Address;
import com.behl.eclair.entity.sub.Article;

import lombok.Data;

@Data
@Document(collection = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 1864834809115905912L;

	@Id
	@Field(name = "_id")
	private UUID id;

	@Field(name = "full_name")
	private String fullName;

	@Field(name = "email_id")
	private String emailId;

	@Field(name = "password")
	private String password;

	@Field(name = "address")
	private Address address;

	@Field(name = "articles")
	private List<Article> articles;

	@Field(name = "created_at")
	private LocalDateTime createdAt;

	@Field(name = "updated_at")
	private LocalDateTime updatedAt;

}
