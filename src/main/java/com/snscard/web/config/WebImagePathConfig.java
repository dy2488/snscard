package com.snscard.web.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebImagePathConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/generatedImages/**").addResourceLocations("file:/root/img/generatedImages/");
        registry.addResourceHandler("/modifyImages/**").addResourceLocations("file:/root/img/modifyImages/");
    }
}
