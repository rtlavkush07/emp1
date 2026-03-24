package com.example.server.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.*;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Employee Management System API")
                        .version("1.0")
                        .description("This API is used to manage employees, authentication, and roles")
                        .contact(new Contact()
                                .name("Developer")
                                .email("dev@example.com")))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Local Server")));
    }
}