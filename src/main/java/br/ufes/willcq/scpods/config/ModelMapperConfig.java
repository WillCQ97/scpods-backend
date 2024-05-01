package br.ufes.willcq.scpods.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {

        var modelmapper = new ModelMapper();
        modelmapper.getConfiguration().setAmbiguityIgnored( true );
        return modelmapper;

    }
}
