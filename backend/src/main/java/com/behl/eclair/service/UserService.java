package com.behl.eclair.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.behl.eclair.dto.request.UserCreationRequestDto;
import com.behl.eclair.dto.request.UserLoginRequestDto;
import com.behl.eclair.dto.request.UserUpdationRequestDto;
import com.behl.eclair.dto.response.TokenResponseDto;
import com.behl.eclair.dto.response.UserDetailsResponseDto;
import com.behl.eclair.entity.User;
import com.behl.eclair.repository.UserRepository;
import com.behl.eclair.security.LoggedInUserDetailProvider;
import com.behl.eclair.security.utility.JwtUtils;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final JwtUtils jwtUtils;
	private final PasswordEncoder passwordEncoder;

	public void create(final UserCreationRequestDto userCreationRequestDto) {
		if (userRepository.findByEmailId(userCreationRequestDto.getEmailId()).isPresent())
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Account already exists with provided email-id");

		final var user = new User();
		user.setId(UUID.randomUUID());
		user.setFullName(userCreationRequestDto.getFullName());
		user.setEmailId(userCreationRequestDto.getEmailId());
		user.setPassword(passwordEncoder.encode(userCreationRequestDto.getPassword()));
		user.setCreatedAt(LocalDateTime.now(ZoneId.of("+00:00")));
		user.setUpdatedAt(LocalDateTime.now(ZoneId.of("+00:00")));
		userRepository.save(user);
	}

	public TokenResponseDto login(final UserLoginRequestDto userLoginRequestDto) {
		final var user = userRepository.findByEmailId(userLoginRequestDto.getEmailId()).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No Account exists with provided email-id"));

		if (!passwordEncoder.matches(userLoginRequestDto.getPassword(), user.getPassword()))
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials provided");

		return TokenResponseDto.builder().accessToken(jwtUtils.generateAccessToken(user))
				.refreshToken(jwtUtils.generateRefreshToken(user)).build();
	}

	public UserDetailsResponseDto retreiveDetails() {
		final var user = userRepository.findById(LoggedInUserDetailProvider.getId()).get();
		return UserDetailsResponseDto.builder().address(user.getAddress()).createdAt(user.getCreatedAt())
				.emailId(user.getEmailId()).fullName(user.getFullName())
				.numberOfArticles(user.getArticles() == null ? 0 : user.getArticles().size())
				.updatedAt(user.getUpdatedAt()).build();
	}

	public void update(final UserUpdationRequestDto userUpdationRequestDto) {
		final var user = userRepository.findById(LoggedInUserDetailProvider.getId()).get();
		user.setFullName(userUpdationRequestDto.getFullName());
		user.setAddress(userUpdationRequestDto.getAddress());
		user.setUpdatedAt(LocalDateTime.now(ZoneId.of("+00:00")));
		userRepository.save(user);
	}

}
