package com.github.lburgazzoli.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @RestController
    public class ServiceController {
        @RequestMapping("/")
        public String greetings() {
            return "Greetings from Spring Boot!";
        }
    }
}
