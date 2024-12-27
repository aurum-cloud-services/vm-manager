package com.aurum.vmmanager;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VmmanagerApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().load();

		String port = dotenv.get("PORT", "2576");

		String rabbitMqHost = dotenv.get("RABBITMQ_HOST");
		String rabbitMqPort = dotenv.get("RABBITMQ_PORT");
		String rabbitMqUser = dotenv.get("RABBITMQ_USER");
		String rabbitMqPasswd = dotenv.get("RABBITMQ_PASSWORD");

		if (rabbitMqHost == null || rabbitMqHost.isEmpty()) throw new RuntimeException("NO RABBITMQ HOST SET");
		if (rabbitMqPort == null || rabbitMqPort.isEmpty()) throw new RuntimeException("NO RABBITMQ PORT SET");
		if (rabbitMqUser == null || rabbitMqUser.isEmpty()) throw new RuntimeException("NO RABBITMQ USER SET");
		if (rabbitMqPasswd == null || rabbitMqPasswd.isEmpty()) throw new RuntimeException("NO RABBITMQ PASSWORD SET");

		System.setProperty("server.port", port);

		System.setProperty("spring.rabbitmq.host", rabbitMqHost);
		System.setProperty("spring.rabbitmq.port", rabbitMqPort);
		System.setProperty("spring.rabbitmq.username", rabbitMqUser);
		System.setProperty("spring.rabbitmq.password", rabbitMqPasswd);

		dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));

		SpringApplication.run(VmmanagerApplication.class, args);
	}

}
