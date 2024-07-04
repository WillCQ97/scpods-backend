package br.ufes.willcq.scpods.config;

import org.springdoc.core.models.GroupedOpenApi;
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

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components( new Components().addSecuritySchemes( "Basic Auth", createAPIKeyScheme() ) )
                .info( new Info()
                        .title( "SCPODS API" )
                        .description( "Backend do Sistema para Cadastro de Projetos relacionados aos Objetivos de Desenvolvimento Sustentável na Universidade Federal do Espírito Santo." )
                        .version( "1.0" ).contact( authorContact() )
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
                .pathsToMatch( "/acoes/**", "/objetivos/**", "/unidades/**" )
                .build();
    }

    @Bean
    public GroupedOpenApi protectedApi() {
        return GroupedOpenApi.builder()
                .group( "Administração" )
                .pathsToMatch( "/submissoes/**", "/usuarios/**" )
                .addOperationCustomizer( ( operation, handlerMethod ) -> operation
                        .addSecurityItem( new SecurityRequirement().addList( "basicAuth" ) ) )
                .build();
    }

    private Contact authorContact() {
        return new Contact()
                .name( "Willian Conceição Queiroz" )
                .email( "willian.cqueiroz@gmail.com" )
                .url( "https://github.com/WillCQ97" );
    }

    private License projectLicense() {
        return new License()
                .name( "MIT License" )
                .url( "https://github.com/WillCQ97/scpods-backend/blob/main/LICENSE" );
    }
}
