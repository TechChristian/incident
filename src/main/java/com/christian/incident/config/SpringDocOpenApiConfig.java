package com.christian.incident.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocOpenApiConfig {
    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Rest Api - Spring Incident")
                                .description("Api for Incident management")
                                .version("1.0.0")
                                .contact(new Contact().name("Christian Lopes ").email("christianx111.ls@gmail.com"))
                );
    }
}
