package com.serverlesskitchen.fayaaz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@Configuration
@ComponentScan({"com.serverlesskitchen.controller","com.serverlesskitchen.service"})
@EnableMongoRepositories("com.serverlesskitchen.repository")
@EntityScan("com.serverlesskitchen.model")
public class FayaazApplication {

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(FayaazApplication.class, args);
    }
}
