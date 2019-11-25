package edu.utep.cs.roach.TicketManagerBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class TicketManagerBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketManagerBackendApplication.class, args);
	}

}
