package com.mounirgaiby.notificationsservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Notifications Service API",
        version = "2.0",
        description = "Microservice for sending email and SMS notifications. " +
                     "V1 endpoints are deprecated and use simulated notifications. " +
                     "V2 endpoints provide real email functionality and extended SMS support.",
        contact = @Contact(
            name = "Notifications Service",
            email = "support@notificationsservice.com"
        )
    ),
    servers = {
        @Server(url = "http://localhost:8081", description = "Local development server")
    }
)
public class SwaggerConfig {
}
