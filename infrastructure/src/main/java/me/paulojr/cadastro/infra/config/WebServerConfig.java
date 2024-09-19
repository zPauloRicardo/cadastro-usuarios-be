package me.paulojr.cadastro.infra.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("me.paulojr.cadastro.infra")
@OpenAPIDefinition(
        info = @Info(title = "Cadastro API", version = "1.0", description = "Backend Cadastro.",
                contact = @Contact(name = "Paulo Ricardo", email = "pauloricardo.jr1@gmail.com")
        )
)
public class WebServerConfig {
}
