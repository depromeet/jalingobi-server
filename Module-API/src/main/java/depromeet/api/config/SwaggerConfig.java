package depromeet.api.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info =
                @Info(
                        title = "자린고비 API 명세서",
                        description = "Depromeet 13기 1팀 API 명세서",
                        version = "v1"),
        servers = {
                @Server(url = "/", description = "Current Server")
        })
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi chatOpenApi() {
        String[] paths = {"/**"};

        return GroupedOpenApi.builder()
                .group("자린고비 API v1")
                .pathsToMatch(paths)
                .addOpenApiCustomiser(buildSecurityOpenApi())
                .build();
    }

    public OpenApiCustomiser buildSecurityOpenApi() {
        return OpenApi ->
                OpenApi.addSecurityItem(new SecurityRequirement().addList("jwt token"))
                        .getComponents()
                        .addSecuritySchemes(
                                "jwt token",
                                new SecurityScheme()
                                        .name("AUTHORIZATION")
                                        .type(SecurityScheme.Type.APIKEY)
                                        .in(SecurityScheme.In.HEADER));
    }
}
