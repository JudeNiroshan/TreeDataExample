package com.holidu.interview.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableCaching
@ComponentScan(basePackages = "com.holidu.interview.assignment.*")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
