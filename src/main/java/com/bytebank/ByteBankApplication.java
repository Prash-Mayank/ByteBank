package com.bytebank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ByteBankApplication {
=======
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * ByteBank — Digital Banking Platform.
 * Entry point for the Spring Boot application.
 */
@SpringBootApplication
@EnableScheduling
@EnableAsync
public class ByteBankApplication {

>>>>>>> 093ee2d (ByteBank V2 project stucture)
    public static void main(String[] args) {
        SpringApplication.run(ByteBankApplication.class, args);
    }
}
