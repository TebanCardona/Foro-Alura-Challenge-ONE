package com.alura.foro.utils.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {
  // @Bean
  // public OpenAPI customOpenApi() {
  // return new OpenAPI().addSecurityItem(new SecurityRequirement().addList("Bear
  // Authentication"))
  // .components(new Components().addSecuritySchemes("Bearer Authentication",
  // createAPIKeyScheme()))
  // .info(new Info().title("My REST API FORO ALURA").description("Challenge
  // dealura").version("0.1").contact(new
  // Contact().name("EstebanCardona").email("EstebanFabi737 @gmail.com"))
  // .license(new License().name("License of API").url("API license URL")));
  // }

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .components(new Components()
            .addSecuritySchemes("bearer-key",
                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
        .info(new Info().title("My REST API FORO ALURA").description("Challenge de alura").version("0.1")
            .contact(new Contact().name("Esteban Cardona").email("EstebanFabi737@gmail.com")));
  }

  @Bean
  public void message() {
    System.out.println("bearer is working");
  }

}