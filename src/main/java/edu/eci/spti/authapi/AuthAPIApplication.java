package edu.eci.spti.authapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"edu.eci.spti.auth"})
public class AuthAPIApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthAPIApplication.class, args);
    }

}
