package com.domain.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Hadist API Documentation")
                        .version("v1.0")
                        .description("Dokumentasi REST API untuk Hadist API")
                        .termsOfService("http://swagger.io/terms/")
                        .contact(new Contact()
                                .name("Developer")
                                .url("https://domain.com")
                                .email("developer@domain.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")));
    }
}
