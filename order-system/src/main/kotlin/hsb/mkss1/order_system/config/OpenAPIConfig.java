package hsb.mkss1.order_system.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("OrderSystem API")
                .version("v0.1")
                .description("A system to order items")
                .contact(new Contact()
                        .name("Max Mustermann")
                        .email("max.mustermann@hs-bremen.de")))
                .servers(List.of(new Server()
                        .url("localhost:8080")
                        .description("Local development server")));
    }
}
