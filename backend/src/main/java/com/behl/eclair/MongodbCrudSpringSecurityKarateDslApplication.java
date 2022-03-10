package com.behl.eclair;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.behl.eclair.repository")
public class MongodbCrudSpringSecurityKarateDslApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongodbCrudSpringSecurityKarateDslApplication.class, args);
	}

}
