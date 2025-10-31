package uz.salikhdev.springprojct.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Social Media", version = "1.0.0",
        contact = @Contact(name = "Backend GOOGLE")),
        security = {@SecurityRequirement(name = "token")},
        servers = {
                @Server(
                        url = "http://localhost:8080/",
                        description = "Local Server"
                ),
                @Server(
                        url = "https://abc.uz/",
                        description = "Production Server"
                )
        }
)
@SecuritySchemes({
        @SecurityScheme(name = "token", type = SecuritySchemeType.HTTP,
                scheme = "bearer", bearerFormat = "JWT")
})
public class SwaggerConfig {
}
