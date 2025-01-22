package com.alura.forohub.infra.springdoc;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                .info(new Info()
                        .title("Foro Hub Api")
                        .description("""
                                lataforma que te permite registrarte como usuario nuevo e iniciar sesión con tu correo electrónico y contraseña. Una vez autenticado, recibirás un token JWT que te dará acceso a todos los endpoints disponibles en la API.
                                Desarrollado por Cristian Jimenez
                                [LinkedIn](www.linkedin.com/in/cristianyovanny) [GitHub](https://github.com/cristianyovanny)
                                """)
                        .contact(new Contact()
                                .name("Cristian Jimenez")
                                .url("https://cristiayovanny.com")
                        )
                );
    }

}
