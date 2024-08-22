package br.ufes.willcq.scpods.config.swagger;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

    @Autowired
    private AppSwaggerProperties appSwaggerProperties;

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components( new Components().addSecuritySchemes( "Basic Auth", createAPIKeyScheme() ) )
                .info( new Info()
                        .title( appSwaggerProperties.getTitle() )
                        .description( appSwaggerProperties.getDescription() )
                        .version( appSwaggerProperties.getVersion() )
                        .contact( authorContact() )
                        .license( projectLicense() ) );
    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme()
                .type( SecurityScheme.Type.HTTP )
                .scheme( "basic" );
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group( "Público" )
                .pathsToMatch( "/acoes/**", "/lotacoes/**", "/objetivos/**", "/unidades/**", "/usuarios/validar-login-ufes" )
                .build();
    }

    @Bean
    public GroupedOpenApi protectedApi() {
        return GroupedOpenApi.builder()
                .group( "Administração" )
                .pathsToMatch( "/submissoes/**", "/usuarios/validar-admin" )
                .addOperationCustomizer( ( operation, handlerMethod ) -> operation
                        .addSecurityItem( new SecurityRequirement().addList( "basicAuth" ) ) )
                .build();
    }

    private Contact authorContact() {
        return new Contact()
                .name( appSwaggerProperties.getAuthor().getName() )
                .email( appSwaggerProperties.getAuthor().getEmail() )
                .url( appSwaggerProperties.getAuthor().getUrl() );
    }

    private License projectLicense() {
        return new License()
                .name( appSwaggerProperties.getLicense().getName() )
                .url( appSwaggerProperties.getLicense().getUrl() );
    }
}
