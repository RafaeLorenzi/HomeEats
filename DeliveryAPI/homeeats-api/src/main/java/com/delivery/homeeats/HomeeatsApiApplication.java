package com.delivery.homeeats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.delivery.homeeats.infrastructure.repository.CustomJpaRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class HomeeatsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeeatsApiApplication.class, args);
	}

}
