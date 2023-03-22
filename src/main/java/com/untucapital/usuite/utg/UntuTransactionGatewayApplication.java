package com.untucapital.usuite.utg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;

@SpringBootApplication
//@EnableScheduling
public class UntuTransactionGatewayApplication {
    static final Logger log = LoggerFactory.getLogger(UntuTransactionGatewayApplication.class);
    public static void main(String[] args) throws ParseException {
        SpringApplication.run(UntuTransactionGatewayApplication.class, args);

        log.info("=========[ Untu Transaction Gateway Started ]==========");
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/")
    String home() {
        return "Untu Transaction Gateway is running!";
    }

}
