package com.fdjloto.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.Scopes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "FDJ Loto API",
                version = "1.0",
                description = "API pour la gestion des utilisateurs et l'authentification OAuth2"
        ),
        security = @SecurityRequirement(name = "BearerAuth") // 🔐 Active l'authentification globale
)
@SecurityScheme(
        name = "BearerAuth",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
@SecurityScheme(
        name = "OAuth2",
        type = SecuritySchemeType.OAUTH2,
        in = SecuritySchemeIn.HEADER
)
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(new io.swagger.v3.oas.models.security.SecurityRequirement().addList("BearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("BearerAuth",
                                new io.swagger.v3.oas.models.security.SecurityScheme()
                                        .name("BearerAuth")
                                        .type(Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .in(In.HEADER)));
    }
}
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
