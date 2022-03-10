package com.behl.eclair.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.behl.eclair.dto.request.ArticleCreationRequestDto;
import com.behl.eclair.entity.sub.Article;
import com.behl.eclair.entity.sub.Tag;
import com.behl.eclair.repository.UserRepository;
import com.behl.eclair.security.LoggedInUserDetailProvider;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ArticleService {

	private final UserRepository userRepository;

	public List<Article> retreive() {
		return userRepository.findById(LoggedInUserDetailProvider.getId()).get().getArticles();
	}

	public void create(final ArticleCreationRequestDto articleCreationRequestDto) {
		final var user = userRepository.findById(LoggedInUserDetailProvider.getId()).get();

		final var article = new Article();
		article.setId(UUID.randomUUID());
		article.setCreatedAt(LocalDateTime.now(ZoneId.of("+00:00")));
		mapArticleValues(articleCreationRequestDto, article);

		if (user.getArticles() == null)
			user.setArticles(List.of(article));
		else
			user.getArticles().add(article);

		userRepository.save(user);
	}

	public void update(final UUID articleId, final ArticleCreationRequestDto articleUpdationRequestDto) {
		final var user = userRepository.findByArticlesId(articleId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

		if (!user.getId().equals(LoggedInUserDetailProvider.getId()))
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

		user.getArticles().parallelStream().filter(article -> article.getId().equals(articleId)).forEach(article -> {
			mapArticleValues(articleUpdationRequestDto, article);
		});

		userRepository.save(user);
	}

	public void delete(final UUID articleId) {
		final var user = userRepository.findByArticlesId(articleId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

		if (!user.getId().equals(LoggedInUserDetailProvider.getId()))
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

		user.setArticles(user.getArticles().parallelStream().filter(article -> !article.getId().equals(articleId))
				.collect(Collectors.toList()));
		userRepository.save(user);
	}

	private void mapArticleValues(final ArticleCreationRequestDto articleCreationUpdationRequestDto,
			final Article article) {
		article.setTitle(articleCreationUpdationRequestDto.getTitle());
		article.setDescription(articleCreationUpdationRequestDto.getDescription());
		article.setTags(Arrays.asList(articleCreationUpdationRequestDto.getTags().split(",")).parallelStream()
				.map(tag -> new Tag(tag.trim().toUpperCase())).collect(Collectors.toList()));
		article.setUpdatedAt(LocalDateTime.now(ZoneId.of("+00:00")));
	}

}
