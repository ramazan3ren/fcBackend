package com.fc.ws;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fc.ws.user.User;
import com.fc.ws.user.UserRepository;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class WsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WsApplication.class, args);
	}

	@Bean
	CommandLineRunner userCreator(UserRepository userRepository) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return (args) -> {
			for (var i = 1; i <= 25; i++) {
				User user = new User();
				user.setUsername("user" + i);
				user.setEmail("user" + i + "@mail.com");
				user.setPassword(passwordEncoder.encode("P4ssword"));
				user.setFirstName("name" + i);
				user.setLastName("lastname" + i);
				user.setImage(null);
				user.setActive(true);
				userRepository.save(user);

			}
		};
	}

}
