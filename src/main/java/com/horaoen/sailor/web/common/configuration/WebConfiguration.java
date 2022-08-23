package com.horaoen.sailor.web.common.configuration;

import com.horaoen.sailor.autoconfigure.interceptor.AuthorizeInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author horaoen
 */
@Configuration(proxyBeanMethods = false)
@Slf4j
public class WebConfiguration implements WebMvcConfigurer {
    @Value("${auth.enabled:false}")
    private boolean authEnabled;

    @Autowired
    private AuthorizeInterceptor authorizeInterceptor;

    @Value("${sailor.file.serve-path:assets/**}")
    private String servePath;
    
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
//                .allowCredentials(true)
//                .maxAge(3600)
//                .allowedHeaders("*");
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (authEnabled) {
            //开发环境忽略签名认证
            registry.addInterceptor(authorizeInterceptor)
                    .excludePathPatterns(getDirServePath());
        }
    }

    private String getDirServePath() {
        // assets/**
        // assets/
        // /usr/local/assets/
        // assets
        return servePath;
    }
}
