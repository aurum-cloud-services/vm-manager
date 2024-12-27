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

		String postgresUri = dotenv.get("POSTGRES_URI");
		String postgresUser = dotenv.get("POSTGRES_USER");
		String postgresPassword = dotenv.get("POSTGRES_PASSWORD");
		String postgresDdlMode = dotenv.get("POSTGRES_DDL_MODE", "validate");

		if (rabbitMqHost == null || rabbitMqHost.isEmpty()) throw new RuntimeException("NO RABBITMQ HOST SET");
		if (rabbitMqPort == null || rabbitMqPort.isEmpty()) throw new RuntimeException("NO RABBITMQ PORT SET");
		if (rabbitMqUser == null || rabbitMqUser.isEmpty()) throw new RuntimeException("NO RABBITMQ USER SET");
		if (rabbitMqPasswd == null || rabbitMqPasswd.isEmpty()) throw new RuntimeException("NO RABBITMQ PASSWORD SET");

		if (postgresUri == null || postgresUri.isEmpty()) throw new RuntimeException("NO POSTGRES URI SET");
		if (postgresUser == null || postgresUser.isEmpty()) throw new RuntimeException("NO POSTGRES USER SET");
		if (postgresPassword == null || postgresPassword.isEmpty()) throw new RuntimeException("NO POSTGRES PASSWORD SET");

		System.setProperty("server.port", port);

		System.setProperty("spring.rabbitmq.host", rabbitMqHost);
		System.setProperty("spring.rabbitmq.port", rabbitMqPort);
		System.setProperty("spring.rabbitmq.username", rabbitMqUser);
		System.setProperty("spring.rabbitmq.password", rabbitMqPasswd);

		System.setProperty("spring.datasource.url", postgresUri);
		System.setProperty("spring.datasource.username", postgresUser);
		System.setProperty("spring.datasource.password", postgresPassword);
		System.setProperty("spring.datasource.driver-class-name", "org.postgresql.Driver");

		System.setProperty("spring.jpa.database-platform", "org.hibernate.dialect.PostgreSQLDialect");
		System.setProperty("spring.jpa.hibernate.ddl-auto", postgresDdlMode);

		dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));

		SpringApplication.run(VmmanagerApplication.class, args);
	}

}
