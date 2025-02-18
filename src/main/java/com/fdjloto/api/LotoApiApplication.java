// filepath: /c:/Users/steph/Documents/portfolio/portfolio/Loto_API/src/main/java/com/fdjloto/api/LotoApiApplication.java
package com.fdjloto.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
// import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
// import io.swagger.v3.oas.models.OpenAPI;
// import io.swagger.v3.oas.models.info.Info;

@SpringBootApplication
@EnableWebMvc
@ComponentScan(basePackages = "com.fdjloto.api")
public class LotoApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(LotoApiApplication.class, args);
    }

    // @Bean
    // public OpenAPI customOpenAPI() {
    //     return new OpenAPI()
    //             .info(new Info().title("Loto API").version("1.0").description("API pour la gestion des utilisateurs avec SQLite3"));
    // }
}
