package com.untucapital.usuite.utg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class UntuTransactionGatewayApplication {

    static final Logger log = LoggerFactory.getLogger(UntuTransactionGatewayApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(UntuTransactionGatewayApplication.class, args);

        log.info("=========[ Untu Transaction Gateway Started ]==========");
    }

    @GetMapping("/")
    String home() {
        return "Untu Transaction Gateway is running!";
    }

}
