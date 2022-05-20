package com.example.mobilezone_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MobileZoneApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MobileZoneApiApplication.class, args);
    }

}
