package com.poorbet.commons.rabbit;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.Map;

@Getter
@Setter
@ConfigurationProperties(prefix = "messaging")
@Validated
public class MessagingProperties {

    private String sourceService;

    private Map<String, String> exchanges;

    private Map<EventKey, ConsumerConfig> consumers;

    @Getter
    @Setter
    public static class ConsumerConfig {
        private String queue;
    }
}