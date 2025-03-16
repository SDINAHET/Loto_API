package com.fdjloto.config;

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
import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Loto Tracker API 🚀",
        version = "v1.0.0",
        description = "API pour suivre les résultats du Loto et gérer les comptes utilisateurs.",
        contact = @Contact(
            name = "Stéphane Dinahet",
            email = "contact@fdjloto.com",
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
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("Loto Tracker API 🚀")
                        .version("v1.0.0")
                        .description("API pour suivre les résultats du Loto et gérer les comptes utilisateurs. <br> <b>Utilisation :</b> <ul><li>Authentification avec JWT</li><li>Gestion des utilisateurs (Admin seulement)</li><li>Accès public pour les résultats du Loto</li></ul>")
                        .contact(new io.swagger.v3.oas.models.info.Contact()
                                .name("Stéphane Dinahet")
                                .email("contact@fdjloto.com")
                                .url("https://github.com/SDINAHET")
                        )
                        .license(new License().name("MIT License").url("https://opensource.org/licenses/MIT"))
                )
                .addSecurityItem(new io.swagger.v3.oas.models.security.SecurityRequirement().addList("bearerAuth", Arrays.asList("read", "write")))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new io.swagger.v3.oas.models.security.SecurityScheme()
                                        .type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        ))
                .addServersItem(new Server().url("http://localhost:8082").description("Serveur local"));
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://127.0.0.1:5500", "http://localhost:8082")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .exposedHeaders("Authorization")
                        .allowCredentials(true);
            }
        };
    }
}
