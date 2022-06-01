package ru.petrov.check_uploader_maxi_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CheckUploaderMaxiTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(CheckUploaderMaxiTestApplication.class, args);
    }

}
