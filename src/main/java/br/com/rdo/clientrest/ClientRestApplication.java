package br.com.rdo.clientrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ClientRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientRestApplication.class, args);
	}

}
