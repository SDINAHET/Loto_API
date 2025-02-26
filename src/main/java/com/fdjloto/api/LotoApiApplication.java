// // filepath: /c:/Users/steph/Documents/portfolio/portfolio/Loto_API/src/main/java/com/fdjloto/api/LotoApiApplication.java
// package com.fdjloto.api;

// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.annotation.ComponentScan;
// // import org.springframework.context.annotation.Bean;
// import org.springframework.web.servlet.config.annotation.EnableWebMvc;
// import org.springframework.boot.autoconfigure.SpringBootApplication;
// // import io.swagger.v3.oas.models.OpenAPI;
// // import io.swagger.v3.oas.models.info.Info;

// // @SpringBootApplication
// @EnableWebMvc
// @ComponentScan(basePackages = "com.fdjloto.api")
// @SpringBootApplication(scanBasePackages = {"com.fdjloto"})
// public class LotoApiApplication {
//     public static void main(String[] args) {
//         SpringApplication.run(LotoApiApplication.class, args);
//     }

//     // @Bean
//     // public OpenAPI customOpenAPI() {
//     //     return new OpenAPI()
//     //             .info(new Info().title("Loto API").version("1.0").description("API pour la gestion des utilisateurs avec SQLite3"));
//     // }
// }


// filepath: /c:/Users/steph/Documents/portfolio/portfolio/Loto_API/src/main/java/com/fdjloto/api/LotoApiApplication.java
package com.fdjloto.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@EnableWebMvc
@ComponentScan(basePackages = "com.fdjloto.api")
@SpringBootApplication(scanBasePackages = {"com.fdjloto"})
@OpenAPIDefinition(
    info = @Info(
        title = "Loto Tracker API 🚀",
        version = "v1.0.0",
        description = "API pour suivre les résultats du Loto et gérer les comptes utilisateurs. <br> <b>Fonctionnalités :</b> <ul><li>🔑 Authentification avec JWT</li><li>👤 Gestion des utilisateurs (Admin seulement)</li><li>📊 Accès public pour les résultats du Loto</li></ul>",
        contact = @Contact(
            name = "Stéphane Dinahet",
            email = "stephane.dinahet@gmail.com",
            url = "https://github.com/SDINAHET"
        )
    ),
    security = {@SecurityRequirement(name = "bearerAuth")}
)
@SecurityScheme(
    name = "bearerAuth",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT"
)
public class LotoApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(LotoApiApplication.class, args);
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("Loto Tracker API 🚀")
                        .version("v1.0.0")
                        .description("API pour suivre les résultats du Loto et gérer les comptes utilisateurs. <br> <b>Utilisation :</b> <ul><li>🔑 Authentification avec JWT</li><li>👤 Gestion des utilisateurs (Admin seulement)</li><li>📊 Accès public pour les résultats du Loto</li></ul>")
                        .contact(new io.swagger.v3.oas.models.info.Contact()
                                .name("Stéphane Dinahet")
                                .email("contact@fdjloto.com")
                                .url("https://github.com/SDINAHET")
                        )
                        .license(new License().name("MIT License").url("https://opensource.org/licenses/MIT"))
                )
                .addSecurityItem(new io.swagger.v3.oas.models.security.SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new io.swagger.v3.oas.models.security.SecurityScheme()
                                        .type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        ))
                .addServersItem(new Server().url("http://localhost:8082").description("Serveur local"));
    }
}
