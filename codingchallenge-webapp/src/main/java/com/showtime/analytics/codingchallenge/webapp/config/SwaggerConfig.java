package com.showtime.analytics.codingchallenge.webapp.config;

import static com.showtime.analytics.codingchallenge.common.constant.ApplicationConstants.API_KEY_HEADER_NAME;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
        .info(apiInfo())
        .components(new Components()
            .addSecuritySchemes("token",
                new SecurityScheme()
                    .type(SecurityScheme.Type.APIKEY)
                    .name(API_KEY_HEADER_NAME)
                    .in(SecurityScheme.In.HEADER))
        );
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
