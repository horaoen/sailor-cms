package com.horaoen.sailor.web.common.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author horaoen
 */
@PropertySource(value = "classpath:config/swagger.properties")
@Configuration
public class SwaggerProperties {
    @Bean(name = "info")
    @ConfigurationProperties(prefix = "swagger.info")
    public Info getApiInfo() {
        return new Info();
    }

    @Bean(name = "externalDocumentation")
    @ConfigurationProperties(prefix = "swagger.documentation")
    public ExternalDocumentation getExternalDoc() {
        return new ExternalDocumentation();
    }
}
