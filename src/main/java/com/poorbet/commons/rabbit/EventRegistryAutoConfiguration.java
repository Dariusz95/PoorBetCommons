package com.poorbet.commons.rabbit;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class EventRegistryAutoConfiguration {

    @Bean
    public EventRegistry eventRegistry() {
        return new EventRegistry();
    }
}