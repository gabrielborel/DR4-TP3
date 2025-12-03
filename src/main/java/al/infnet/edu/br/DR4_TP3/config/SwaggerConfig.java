package al.infnet.edu.br.DR4_TP3.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("PetFriends API - DR4-TP3")
                        .description("API para gerenciamento de pedidos do sistema PetFriends utilizando Event Sourcing com Axon Framework")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Gabriel Borel")
                                .email("gabriel@infnet.edu.br"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Servidor de Desenvolvimento")));
    }
}
