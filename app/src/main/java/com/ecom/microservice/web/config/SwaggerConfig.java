package com.ecom.microservice.web.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
    name = SwaggerConfig.BEARER_AUTH,
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "bearer"
)
@OpenAPIDefinition(info = @Info(title = "Security scheme", version = "1.0.0"), security = {
    @SecurityRequirement(name = "bearer_auth")})
public class SwaggerConfig {
    public static final String BEARER_AUTH = "bearer_auth";
}
