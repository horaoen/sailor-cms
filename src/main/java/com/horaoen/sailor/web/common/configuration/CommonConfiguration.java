package com.horaoen.sailor.web.common.configuration;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.horaoen.sailor.autoconfigure.bean.PermissionMetaCollector;
import com.horaoen.sailor.web.module.file.FileProperties;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author pedro@TaleLin
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(FileProperties.class)
public class CommonConfiguration {

//    @Bean
//    public RequestLogInterceptor requestLogInterceptor() {
//        return new RequestLogInterceptor();
//    }

//    @Bean
//    public PaginationInterceptor paginationInterceptor() {
//        return new PaginationInterceptor();
//    }

//    @Bean
//    public ISqlInjector sqlInjector() {
//        return new DefaultSqlInjector();
//    }

    /**
     * 记录每个被 @PermissionMeta 记录的信息，在beans的后置调用
     *
     * @return PermissionMetaCollector
     */
    @Bean
    public PermissionMetaCollector postProcessBeans() {
        return new PermissionMetaCollector();
    }


    /**
     * 接口中，自动转换的有：驼峰转换为下划线，空值输出null
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customJackson() {
        return jacksonObjectMapperBuilder -> {
            // jacksonObjectMapperBuilder.serializationInclusion(JsonInclude.Include.NON_NULL);
            jacksonObjectMapperBuilder.failOnUnknownProperties(false);
            jacksonObjectMapperBuilder.propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        };
    }
}
