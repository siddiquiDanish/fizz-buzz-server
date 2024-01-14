package com.fizzbuzz.fizzbuzzserver.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(title = "Fizz Buzz REST Server",
        description = "A robust and production-ready Fizz-Buzz REST Server",
        contact = @Contact(name = "Danish Ahmed Siddiqui",
                            email = "dexterousdanish@gmail.com",
                            url = "https://www.linkedin.com/in/danish-javafsd/"),
        version = "v1"))
public class OpenApiConfig {
}
