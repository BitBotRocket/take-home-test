package io.swagger.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-10-08T19:58:21.879000852Z[GMT]")
@Configuration
public class SwaggerDocumentationConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
            .info(new Info()
                .title("Acme Sales and Inventory System")
                .description("Simple REST API contract for the Acme Product Management and Sales Retail Company     We'll make some up-front assumptions: - normally a critical aspect of REST APIs, but for this demonstration we will disable RBAC security - bulk provisioning could be accomplished via a future extension of the POST /products API via   POST /products/bulk - additional (optional) parameters were intentionally provided in some of the APIs to add support for    paginated responses and basic filtering")
                .termsOfService("")
                .version("1.0.5")
                .license(new License()
                    .name("MIT")
                    .url("https://opensource.org/license/mit"))
                .contact(new io.swagger.v3.oas.models.info.Contact()
                    .email("adams.dave.m@gmail.com")));
    }

}
