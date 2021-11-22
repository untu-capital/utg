package com.untucapital.usuite.utg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class UntuTransactionGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(UntuTransactionGatewayApplication.class, args);
	}

	@GetMapping("/")
	String home() {
		return "Untu Transaction Gateway is running!";
	}

}
