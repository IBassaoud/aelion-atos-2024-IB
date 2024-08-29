package fr.org.aelion.atos2024.cyber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Fait un component scan @ComponentScan(current package)
public class SpringCyberApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCyberApplication.class, args);
	}

}
