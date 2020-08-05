package com.br.auth;

import com.br.auth.model.entity.User;
import com.br.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class AuthApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		User user = new User();
//		user.setAdmin(true);
//		user.setUpdatedDate(new Date());
//		user.setCreatedDate(new Date());
//		user.setEmail("neto@email.com");
//		user.setLogin("neto");
//		user.setName("Neto");
//		user.setPassword("$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6");
//
//		this.userRepository.save(user);
	}
}
