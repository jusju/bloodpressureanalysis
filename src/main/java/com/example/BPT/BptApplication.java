package com.example.BPT;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.BPT.domain.HealthRecords;
import com.example.BPT.domain.HealthRecordsRepository;
import com.example.BPT.domain.User;
import com.example.BPT.domain.UserRepository;

@SpringBootApplication
public class BptApplication {

	@Autowired
	private HealthRecordsRepository repository;

	@Autowired
	private UserRepository urepository;

	public static void main(String[] args) {
		SpringApplication.run(BptApplication.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {

			repository.save(new HealthRecords("Masih", 98, 65, 65, LocalDate.of(2021, 01, 01)));
			repository.save(new HealthRecords("Jack", 115, 75, 75, LocalDate.of(2020, 12, 28)));
			repository.save(new HealthRecords("Joe", 95, 150, 70, LocalDate.of(2020, 12, 20)));

			urepository.save(new User("user", "$2a$10$BNWuNqBl/XdI7kwirIZp4u8TsJ6kD84se/36MDT0nRuML36Hc1cLq", "USER"));

			urepository.save(new User("admin", "$2a$10$vGdLiDZ2m2k2TrgX/1WnL.RYOeLGy094/4q9VKC8S6qdExc5Cy1ve", "ADMIN"));

		};
	}
}
