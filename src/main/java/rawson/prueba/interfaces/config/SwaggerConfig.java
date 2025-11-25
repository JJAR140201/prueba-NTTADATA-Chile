package rawson.prueba.interfaces.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

/**
 * CONFIGURACIÓN SWAGGER / OPENAPI
 * 
 * Define la documentación interactiva de la API.
 */
@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API REST de Usuarios",
                version = "1.0.0",
                description = "API RESTful para gestión de usuarios con autenticación JWT. Arquitectura Hexagonal.",
                contact = @Contact(
                        name = "NTTA Data Chile",
                        url = "https://www.nttadata.com"
                )
        ),
        servers = {
                @Server(url = "http://localhost:8081", description = "Development Server"),
                @Server(url = "https://api.example.com", description = "Production Server")
        }
)
@SecurityScheme(
        name = "Bearer",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        description = "JWT token obtenido al crear un usuario",
        in = SecuritySchemeIn.HEADER
)
public class SwaggerConfig {
}
