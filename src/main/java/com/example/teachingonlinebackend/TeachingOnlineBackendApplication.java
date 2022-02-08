package com.example.teachingonlinebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi
public class TeachingOnlineBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeachingOnlineBackendApplication.class, args);
    }

}
