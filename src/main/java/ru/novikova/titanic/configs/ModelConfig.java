package ru.novikova.titanic.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@Configuration
public class ModelConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}