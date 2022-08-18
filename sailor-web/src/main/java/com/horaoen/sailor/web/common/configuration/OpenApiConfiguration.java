package com.horaoen.sailor.web.common.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author horaoen
 */
@Configuration
@ConfigurationProperties(prefix = "swagger")
@PropertySource(value = "classpath:config/swagger.properties")
public class OpenApiConfiguration {
    private final Info info;
    private final ExternalDocumentation externalDocumentation;

    public OpenApiConfiguration(Info info, ExternalDocumentation externalDocumentation) {
        this.info = info;
        this.externalDocumentation = externalDocumentation;
    }

    @Bean
    public OpenAPI sailorOpenApi() {
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(
                        new Components()
                                .addSecuritySchemes(securitySchemeName,
                                        new SecurityScheme()
                                                .name(securitySchemeName)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )


                )
                .info(info)
                .externalDocs(externalDocumentation);
    }


    @Bean
    public GroupedOpenApi cmsApi() {
        return GroupedOpenApi.builder()
                .group("cms")
                .pathsToMatch("/cms/**")
                .build();
    }

    @Bean
    public GroupedOpenApi v2Api() {
        return GroupedOpenApi.builder()
                .group("v2")
                .pathsToMatch("/v2/**   ")
                .build();
    }
}
