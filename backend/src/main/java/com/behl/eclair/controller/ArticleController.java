package com.behl.eclair.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.behl.eclair.dto.request.ArticleCreationRequestDto;
import com.behl.eclair.entity.sub.Article;
import com.behl.eclair.service.ArticleService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/articles")
public class ArticleController {

	private final ArticleService articleService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	@Operation(summary = "retreives all articles created by the logged in user")
	public ResponseEntity<List<Article>> articlesRetreivalHandler() {
		return ResponseEntity.ok(articleService.retreive());
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	@Operation(summary = "creates an article")
	public ResponseEntity<?> articleCreationHandler(
			@RequestBody(required = true) final ArticleCreationRequestDto articleCreationRequestDto) {
		articleService.create(articleCreationRequestDto);
		return ResponseEntity.ok().build();
	}

	@PutMapping(value = "/{articleId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	@Operation(summary = "updates an article")
	public ResponseEntity<?> articleUpdationHandler(
			@PathVariable(name = "articleId", required = true) final UUID articleId,
			@RequestBody(required = true) final ArticleCreationRequestDto articleUpdationRequestDto) {
		articleService.update(articleId, articleUpdationRequestDto);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(value = "/{articleId}")
	@ResponseStatus(value = HttpStatus.OK)
	@Operation(summary = "deletes an article")
	public ResponseEntity<?> articleDeletionHandler(
			@PathVariable(name = "articleId", required = true) final UUID articleId) {
		articleService.delete(articleId);
		return ResponseEntity.ok().build();
	}

}
