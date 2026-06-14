package com.bytebank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ByteBankApplication {
    public static void main(String[] args) {
        SpringApplication.run(ByteBankApplication.class, args);
    }
}
