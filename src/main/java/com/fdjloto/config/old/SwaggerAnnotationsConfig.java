package com.fdjloto.config.old;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "FDJ Loto API",
                version = "1.0",
                description = "API pour la gestion des utilisateurs et l'authentification JWT"
        ),
        security = @SecurityRequirement(name = "BearerAuth") // 🔐 Activation globale du token
)
@SecurityScheme(
        name = "BearerAuth",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,  // ✅ Corrigé
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER // ✅ Corrigé
)
public class SwaggerAnnotationsConfig {
    // Ce fichier ne contient que les annotations Swagger.
}
