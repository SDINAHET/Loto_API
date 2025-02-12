// package com.fdjloto.config;

// import io.swagger.v3.oas.annotations.OpenAPIDefinition;
// import io.swagger.v3.oas.annotations.info.Info;
// import io.swagger.v3.oas.annotations.security.SecurityRequirement;
// import io.swagger.v3.oas.annotations.security.SecurityScheme;
// import io.swagger.v3.oas.annotations.security.SecuritySchemes;
// import io.swagger.v3.oas.models.OpenAPI;
// import io.swagger.v3.oas.models.Components;
// import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
// import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
// import io.swagger.v3.oas.models.security.SecurityScheme.Type;
// import io.swagger.v3.oas.models.security.SecurityScheme.In;
// import io.swagger.v3.oas.models.security.OAuthFlows;
// import io.swagger.v3.oas.models.security.OAuthFlow;
// import io.swagger.v3.oas.models.security.Scopes;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// @Configuration
// @OpenAPIDefinition(
//         info = @Info(
//                 title = "FDJ Loto API",
//                 version = "1.0",
//                 description = "API pour la gestion des utilisateurs et l'authentification OAuth2"
//         ),
//         security = @SecurityRequirement(name = "BearerAuth") // 🔐 Active l'authentification globale
// )
// @SecurityScheme(
//         name = "BearerAuth",
//         scheme = "bearer",
//         type = SecuritySchemeType.HTTP,
//         bearerFormat = "JWT",
//         in = SecuritySchemeIn.HEADER
// )
// @SecurityScheme(
//         name = "OAuth2",
//         type = SecuritySchemeType.OAUTH2,
//         in = SecuritySchemeIn.HEADER
// )
// public class OpenApiConfig {

//     @Bean
//     public OpenAPI customOpenAPI() {
//         return new OpenAPI()
//                 .addSecurityItem(new io.swagger.v3.oas.models.security.SecurityRequirement().addList("BearerAuth"))
//                 .components(new Components()
//                         .addSecuritySchemes("BearerAuth",
//                                 new io.swagger.v3.oas.models.security.SecurityScheme()
//                                         .name("BearerAuth")
//                                         .type(Type.HTTP)
//                                         .scheme("bearer")
//                                         .bearerFormat("JWT")
//                                         .in(In.HEADER)));
//     }
// }
// package com.fdjloto.config;

// import io.swagger.v3.oas.annotations.OpenAPIDefinition;
// import io.swagger.v3.oas.annotations.info.Info;
// import io.swagger.v3.oas.annotations.security.SecurityRequirement;
// import io.swagger.v3.oas.annotations.security.SecurityScheme;
// import io.swagger.v3.oas.annotations.security.SecuritySchemes;
// import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
// import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
// import io.swagger.v3.oas.models.OpenAPI;
// import io.swagger.v3.oas.models.Components;
// import io.swagger.v3.oas.models.security.SecurityRequirement;
// import io.swagger.v3.oas.models.security.SecurityScheme;
// import io.swagger.v3.oas.models.security.OAuthFlows;
// import io.swagger.v3.oas.models.security.OAuthFlow;
// import io.swagger.v3.oas.models.security.Scopes;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// @Configuration
// @OpenAPIDefinition(
//         info = @Info(
//                 title = "FDJ Loto API",
//                 version = "1.0",
//                 description = "API pour la gestion des utilisateurs et l'authentification OAuth2"
//         ),
//         security = @SecurityRequirement(name = "BearerAuth")
// )
// @SecuritySchemes({
//     @SecurityScheme(
//             name = "BearerAuth",
//             scheme = "bearer",
//             type = SecuritySchemeType.HTTP,
//             bearerFormat = "JWT",
//             in = SecuritySchemeIn.HEADER
//     ),
//     @SecurityScheme(
//             name = "OAuth2",
//             type = SecuritySchemeType.OAUTH2,
//             in = SecuritySchemeIn.HEADER,
//             flows = @io.swagger.v3.oas.annotations.security.OAuthFlows(
//                     authorizationCode = @io.swagger.v3.oas.annotations.security.OAuthFlow(
//                             authorizationUrl = "https://example.com/oauth/authorize",
//                             tokenUrl = "https://example.com/oauth/token",
//                             scopes = {
//                                     @io.swagger.v3.oas.annotations.security.OAuthScope(name = "read", description = "Lire les données"),
//                                     @io.swagger.v3.oas.annotations.security.OAuthScope(name = "write", description = "Écrire les données")
//                             }
//                     )
//             )
//     )
// })
// public class SwaggerConfig {

//     @Bean
//     public OpenAPI customOpenAPI() {
//         return new OpenAPI()
//                 .addSecurityItem(new SecurityRequirement().addList("BearerAuth"))
//                 .addSecurityItem(new SecurityRequirement().addList("OAuth2"))
//                 .components(new Components()
//                         .addSecuritySchemes("BearerAuth",
//                                 new SecurityScheme()
//                                         .name("BearerAuth")
//                                         .type(SecurityScheme.Type.HTTP)
//                                         .scheme("bearer")
//                                         .bearerFormat("JWT")
//                                         .in(SecurityScheme.In.HEADER))
//                         .addSecuritySchemes("OAuth2",
//                                 new SecurityScheme()
//                                         .type(SecurityScheme.Type.OAUTH2)
//                                         .flows(new OAuthFlows()
//                                                 .authorizationCode(new OAuthFlow()
//                                                         .authorizationUrl("https://example.com/oauth/authorize")
//                                                         .tokenUrl("https://example.com/oauth/token")
//                                                         .scopes(new Scopes()
//                                                                 .addString("read", "Lire les données")
//                                                                 .addString("write", "Écrire les données"))))));
//     }
// }

// package com.fdjloto.config;

// import io.swagger.v3.oas.models.OpenAPI;
// import io.swagger.v3.oas.models.info.Info;
// import io.swagger.v3.oas.models.security.SecurityRequirement;
// import io.swagger.v3.oas.models.security.SecurityScheme;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// @Configuration
// public class OpenApiConfig {
//     @Bean
//     public OpenAPI customOpenAPI() {
//         return new OpenAPI()
//                 .info(new Info().title("API Documentation")
//                         .version("1.0")
//                         .description("Documentation de l'API sécurisée avec JWT"))
//                 .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
//                 .components(new io.swagger.v3.oas.models.Components()
//                         .addSecuritySchemes("bearerAuth", new SecurityScheme()
//                                 .name("bearerAuth")
//                                 .type(SecurityScheme.Type.HTTP)
//                                 .scheme("bearer")
//                                 .bearerFormat("JWT")));
//     }
// }

// package com.fdjloto.config;

// import io.swagger.v3.oas.annotations.OpenAPIDefinition;
// import io.swagger.v3.oas.annotations.info.Info;
// import io.swagger.v3.oas.annotations.security.SecurityRequirement;
// import io.swagger.v3.oas.annotations.security.SecurityScheme;
// import io.swagger.v3.oas.models.OpenAPI;
// import io.swagger.v3.oas.models.Components;
// import io.swagger.v3.oas.models.security.SecurityScheme.Type;
// import io.swagger.v3.oas.models.security.SecurityScheme.In;
// // import io.swagger.v3.oas.models.security.SecurityRequirement;
// // import io.swagger.v3.oas.models.security.SecurityScheme;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// @Configuration
// @OpenAPIDefinition(
//         info = @Info(
//                 title = "FDJ Loto API",
//                 version = "1.0",
//                 description = "API pour la gestion des utilisateurs et l'authentification OAuth2"
//         ),
//         security = @SecurityRequirement(name = "BearerAuth") // 🔐 Active l'authentification globale dans Swagger
// )
// // @SecurityScheme(
// //         name = "BearerAuth",
// //         scheme = "bearer",
// //         type = SecurityScheme.Type.HTTP,
// //         bearerFormat = "JWT",
//         // in = SecuritySchemeIn.HEADER
// // )
// public class OpenApiConfig {

//     @Bean
//     public OpenAPI customOpenAPI() {
//         return new OpenAPI()
//                 .addSecurityItem(new SecurityRequirement().addList("BearerAuth"))
//                 .components(new Components()
//                         .addSecuritySchemes("BearerAuth",
//                                 new SecurityScheme()
//                                         .name("BearerAuth")
//                                         .type(Type.HTTP)
//                                         .scheme("bearer")
//                                         .bearerFormat("JWT")
//                                         .in(In.HEADER)));
//     }
// }

// package com.fdjloto.config;

// import io.swagger.v3.oas.annotations.OpenAPIDefinition;
// import io.swagger.v3.oas.annotations.info.Info;
// import io.swagger.v3.oas.annotations.security.SecurityRequirement;
// import io.swagger.v3.oas.annotations.security.SecurityScheme;
// import io.swagger.v3.oas.models.OpenAPI;
// import io.swagger.v3.oas.models.Components;
// import io.swagger.v3.oas.models.security.SecurityScheme.Type;
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
//         security = @SecurityRequirement(name = "bearerAuth")  // 🔐 Applique JWT globalement
// )
// @SecurityScheme(
//         name = "bearerAuth",
//         scheme = "bearer",
//         type = SecurityScheme.Type.HTTP,
//         bearerFormat = "JWT"
// )
// public class OpenApiConfig {

//     @Bean
//     public OpenAPI customOpenAPI() {
//         return new OpenAPI()
//                 .addSecurityItem(new SecurityRequirement().addList("bearerAuth")) // 🔥 Correcte
//                 .components(new Components()
//                         .addSecuritySchemes("bearerAuth",
//                                 new SecurityScheme()
//                                         .type(Type.HTTP)
//                                         .scheme("bearer")
//                                         .bearerFormat("JWT")));
//     }
// }

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
//         security = @SecurityRequirement(name = "bearerAuth")  // 🔐 Active JWT pour toute l'API
// )
// @SecurityScheme(
//         name = "bearerAuth",
//         scheme = "bearer",
//         type = SecuritySchemeType.HTTP,
//         bearerFormat = "JWT",
//         in = SecuritySchemeIn.HEADER
// )
// public class OpenApiConfig {

//     @Bean
//     public OpenAPI customOpenAPI() {
//         return new OpenAPI()
//                 .components(new Components()
//                         .addSecuritySchemes("bearerAuth",
//                                 new SecurityScheme()
//                                         .type(SecurityScheme.Type.HTTP)
//                                         .scheme("bearer")
//                                         .bearerFormat("JWT")
//                                         .in(SecurityScheme.In.HEADER)))
//                 .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
//     }
// }

// package com.fdjloto.config;

// import io.swagger.v3.oas.annotations.OpenAPIDefinition;
// import io.swagger.v3.oas.annotations.security.SecurityRequirement;
// import io.swagger.v3.oas.annotations.security.SecurityScheme;
// import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
// import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
// import io.swagger.v3.oas.models.OpenAPI;
// import io.swagger.v3.oas.models.Components;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// @Configuration
// // @SecurityRequirement(name = "BearerAuth")

// @OpenAPIDefinition(security = { @SecurityRequirement(name = "bearerAuth") })
// @SecurityScheme(
//         name = "bearerAuth",
//         scheme = "bearer",
//         type = SecuritySchemeType.HTTP,
//         bearerFormat = "JWT",
//         in = SecuritySchemeIn.HEADER
// )
// public class OpenApiConfig {

//     @Bean
//     public OpenAPI customOpenAPI() {
//         return new OpenAPI()
//                 .components(new Components()
//                         .addSecuritySchemes("bearerAuth",
//                                 new io.swagger.v3.oas.models.security.SecurityScheme() // ✅ Référence complète
//                                         .type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP)
//                                         .scheme("bearer")
//                                         .bearerFormat("JWT")
//                                         .in(io.swagger.v3.oas.models.security.SecurityScheme.In.HEADER)))
//                 .addSecurityItem(new io.swagger.v3.oas.models.security.SecurityRequirement().addList("bearerAuth"));
//     }
// }

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
//         security = @SecurityRequirement(name = "bearerAuth")  // 🔐 Applique JWT globalement à l'API
// )
// @SecurityScheme(
//         name = "bearerAuth",
//         scheme = "bearer",
//         type = SecuritySchemeType.HTTP,
//         bearerFormat = "JWT",
//         in = SecuritySchemeIn.HEADER
// )
// public class OpenApiConfig {

//     @Bean
//     public OpenAPI customOpenAPI() {
//         return new OpenAPI()
//                 .addSecurityItem(new SecurityRequirement().addList("bearerAuth")) // 🔥 Applique JWT aux endpoints
//                 .components(new Components()
//                         .addSecuritySchemes("bearerAuth",
//                                 new SecurityScheme()
//                                         .type(SecuritySchemeType.HTTP)
//                                         .scheme("bearer")
//                                         .bearerFormat("JWT")
//                                         .in(SecuritySchemeIn.HEADER)));
//     }
// }





