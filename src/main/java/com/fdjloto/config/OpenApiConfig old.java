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
// import io.swagger.v3.oas.models.security.SecurityScheme.Type;
// import io.swagger.v3.oas.models.security.SecurityScheme.In;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// @Configuration
// @OpenAPIDefinition(
//         info = @Info(
//                 title = "FDJ Loto API",
//                 version = "1.0",
//                 description = "API pour la gestion des utilisateurs et l'authentification JWT"
//         ),
//         security = { @SecurityRequirement(name = "bearerAuth") }  // 🔐 JWT activé globalement
// )
// @SecurityScheme(
//         name = "bearerAuth",
//         scheme = "bearer",
//         type = SecuritySchemeType.HTTP,
//         bearerFormat = "JWT",
//         in = SecuritySchemeIn.HEADER
// )
// // @SecuritySchemes({
// //     @SecurityScheme(
// //         name = "bearerAuth",
// //         scheme = "bearer",
// //         type = SecuritySchemeType.HTTP,
// //         bearerFormat = "JWT",
// //         in = SecuritySchemeIn.HEADER
// //     )
// // })

// public class OpenApiConfig {

//     @Bean
//     public OpenAPI customOpenAPI() {
//         return new OpenAPI()
//                 .addSecurityItem(new io.swagger.v3.oas.models.security.SecurityRequirement()
//                         .addList("bearerAuth")) // ✅ Applique JWT sur Swagger UI
//                 .components(new Components()
//                         .addSecuritySchemes("bearerAuth",
//                                 new io.swagger.v3.oas.models.security.SecurityScheme()
//                                         .type(Type.HTTP)  // ✅ Utilisation correcte
//                                         .scheme("bearer")
//                                         .bearerFormat("JWT")
//                                         .in(In.HEADER)  // ✅ Utilisation correcte
//                         )
//                 );
//     }
// }

// // package com.fdjloto.config;

// // import io.swagger.v3.oas.annotations.OpenAPIDefinition;
// // import io.swagger.v3.oas.annotations.info.Info;
// // import io.swagger.v3.oas.annotations.security.SecurityRequirement;
// // import io.swagger.v3.oas.annotations.security.SecurityScheme;
// // import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
// // import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
// // import io.swagger.v3.oas.models.OpenAPI;
// // import io.swagger.v3.oas.models.Components;
// // import io.swagger.v3.oas.models.security.SecurityRequirement;
// // import io.swagger.v3.oas.models.security.SecurityScheme;
// // import org.springframework.context.annotation.Bean;
// // import org.springframework.context.annotation.Configuration;

// // @Configuration
// // @OpenAPIDefinition(
// //         info = @Info(
// //                 title = "FDJ Loto API",
// //                 version = "1.0",
// //                 description = "API pour la gestion des utilisateurs et l'authentification JWT"
// //         ),
// //         security = { @SecurityRequirement(name = "bearerAuth") } // 🔐 JWT activé globalement
// // )
// // @SecurityScheme(
// //         name = "bearerAuth",
// //         scheme = "bearer",
// //         type = SecuritySchemeType.HTTP,
// //         bearerFormat = "JWT",
// //         in = SecuritySchemeIn.HEADER
// // )
// // public class OpenApiConfig {

// //     @Bean
// //     public OpenAPI customOpenAPI() {
// //         return new OpenAPI()
// //                 .addSecurityItem(new SecurityRequirement().addList("bearerAuth")) // ✅ Applique JWT sur Swagger UI
// //                 .components(new Components()
// //                         .addSecuritySchemes("bearerAuth",
// //                                 new SecurityScheme()
// //                                         .type(SecurityScheme.Type.HTTP)  // ✅ Utilisation correcte
// //                                         .scheme("bearer")
// //                                         .bearerFormat("JWT")
// //                                         .in(SecurityScheme.In.HEADER)  // ✅ Utilisation correcte
// //                         )
// //                 );
// //     }
// // }


