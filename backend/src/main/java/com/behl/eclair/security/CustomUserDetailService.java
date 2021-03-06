package com.behl.eclair.security;

import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.behl.eclair.repository.UserRepository;
import com.behl.eclair.security.utility.SecurityUtils;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(final String userId) throws UsernameNotFoundException {
		return SecurityUtils.convert(userRepository.findById(UUID.fromString(userId))
				.orElseThrow(() -> new UsernameNotFoundException("Invalid User-id provided")));
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}