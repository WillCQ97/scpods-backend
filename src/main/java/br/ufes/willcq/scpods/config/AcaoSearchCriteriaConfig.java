package br.ufes.willcq.scpods.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.ufes.willcq.scpods.api.util.AcaoSearchCriteriaHandler;

@Configuration
public class AcaoSearchCriteriaConfig {

    @Bean
    public AcaoSearchCriteriaHandler acaoSearchCriteriaHandler() {
        return new AcaoSearchCriteriaHandler();
    }
}
