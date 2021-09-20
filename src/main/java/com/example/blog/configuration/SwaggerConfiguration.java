package com.example.blog.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@EnableSwagger2
@Configuration
@EnableScheduling
public class SwaggerConfiguration implements WebMvcConfigurer {

    @Bean
    public Docket swaggerApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.example.blog"))
                .build()
                .apiInfo(apiDetails());

    }

    private ApiInfo apiDetails(){
        return new ApiInfo(
                "Blog API",
                "Sample API for BLog post",
                "1.0",
                "Free to use",
                new springfox.documentation.service.Contact("dfadasLove", "http:localhost", "dfadaslove@gmail.com"),
                "API License",
                "http://localhost8080",
                Collections.emptyList());

    }


}
