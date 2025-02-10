package com.fdjloto.config.old;
// package com.fdjloto.config;

// import io.swagger.v3.oas.annotations.OpenAPIDefinition;
// import io.swagger.v3.oas.annotations.info.Info;
// import io.swagger.v3.oas.annotations.security.SecurityRequirement;
// import io.swagger.v3.oas.annotations.security.SecurityScheme;
// import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
// import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
// import io.swagger.v3.oas.models.OpenAPI;
// import io.swagger.v3.oas.models.Components;
// import io.swagger.v3.oas.models.security.SecurityRequirement;
// import io.swagger.v3.oas.models.security.SecurityScheme;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// @Configuration
// @OpenAPIDefinition(
//         info = @Info(
//                 title = "FDJ Loto API",
//                 version = "1.0",
//                 description = "API pour la gestion des utilisateurs et l'authentification JWT"
//         ),
//         security = @SecurityRequirement(name = "BearerAuth") // 🔐 Activation globale du token
// )
// @SecurityScheme(
//         name = "BearerAuth",
//         scheme = "bearer",
//         type = SecuritySchemeType.HTTP,  // ✅ Correct
//         bearerFormat = "JWT",
//         in = SecuritySchemeIn.HEADER // ✅ Correct
// )
// public class SwaggerConfig {

//     @Bean
//     public OpenAPI customOpenAPI() {
//         return new OpenAPI()
//                 .addSecurityItem(new SecurityRequirement().addList("BearerAuth")) // 🔐 Ajout du schéma de sécurité
//                 .components(new Components()
//                         .addSecuritySchemes("BearerAuth",
//                                 new SecurityScheme()
//                                         .type(SecurityScheme.Type.HTTP)
//                                         .scheme("bearer")
//                                         .bearerFormat("JWT")
//                                         .in(SecurityScheme.In.HEADER))); // ✅ Activation du cadenas 🔐
//     }
// }
