package com.CustomerService;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CustomerServiceApplication {
    private static final Logger log = LoggerFactory.getLogger(CustomerServiceApplication.class);

    @Value("${message}")
    private String message;

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @PostConstruct
    public void displayMessage() {
        log.info(message);
    }
}