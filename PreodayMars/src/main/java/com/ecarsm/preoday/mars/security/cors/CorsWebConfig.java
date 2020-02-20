package com.ecarsm.preoday.mars.security.cors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsWebConfig implements WebMvcConfigurer {

    @Value("${cors.origin}")
    private String origin;

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .allowedOrigins(this.origin)
                .allowedMethods("GET", "POST", "OPTIONS", "PUT", "DELETE")
                .allowedHeaders("Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization", "TenantID")
                .allowCredentials(true)
                .maxAge(3600);
    }

}
