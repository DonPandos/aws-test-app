package com.awstest.springboot;

import com.awstest.springboot.entity.User;
import com.awstest.springboot.repository.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.aws.autoconfigure.context.ContextInstanceDataAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(
		exclude = ContextInstanceDataAutoConfiguration.class
)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public ApplicationRunner applicationRunner(UserRepository userRepository) {
		return args -> {
			for (int i = 0; i < 10000; i++) {
				userRepository.save(new User("User" + i, "Password" + i));
			}
		};
	}
}
