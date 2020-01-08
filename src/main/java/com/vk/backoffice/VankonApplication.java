package com.vk.backoffice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VankonApplication {

    public static void main(String[] args) {
        SpringApplication.run(VankonApplication.class, args);
    }

}
