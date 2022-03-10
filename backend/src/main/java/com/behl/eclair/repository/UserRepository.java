package com.behl.eclair.repository;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.behl.eclair.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, UUID> {

}