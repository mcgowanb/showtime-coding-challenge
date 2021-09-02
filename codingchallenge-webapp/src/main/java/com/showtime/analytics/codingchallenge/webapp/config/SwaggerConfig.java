package com.showtime.analytics.codingchallenge.webapp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
        .info(apiInfo());
  }

  private Info apiInfo() {
    return new Info().title("Coding Challenge")
        .description("Showtime Analytics Coding Challenge")
        .version("1.0.0")
        .contact(new Contact()
            .name("Brian McGowan")
            .email("mcgowan.b@gmail.com")
        );
  }
}
