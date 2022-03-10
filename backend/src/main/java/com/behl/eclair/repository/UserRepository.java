package com.behl.eclair.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.behl.eclair.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, UUID> {

	Optional<User> findByEmailId(final String emailId);

	Optional<User> findByArticlesId(final UUID articleId);

}