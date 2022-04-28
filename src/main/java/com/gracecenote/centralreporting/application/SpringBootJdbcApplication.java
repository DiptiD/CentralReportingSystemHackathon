package com.gracecenote.centralreporting.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages={"com.gracecenote"})
public class SpringBootJdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJdbcApplication.class, args);
	}
}
