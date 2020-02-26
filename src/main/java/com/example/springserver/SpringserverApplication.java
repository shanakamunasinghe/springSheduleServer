package com.example.springserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
public class SpringserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringserverApplication.class, args);
    }

}
