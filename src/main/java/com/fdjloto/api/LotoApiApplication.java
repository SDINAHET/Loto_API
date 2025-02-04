package com.fdjloto.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(
    title = "Loto API",
    version = "1.0",
    description = "API pour récupérer et analyser les résultats du loto"
))
@SpringBootApplication
public class LotoApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(LotoApiApplication.class, args);
    }
}

