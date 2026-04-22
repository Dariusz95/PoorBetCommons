package com.poorbet.commons.rabbit;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class MessagingConfigurationValidator {

    private final MessagingProperties properties;
    private final EventRegistry eventRegistry;

    @EventListener(ApplicationReadyEvent.class)
    public void validate() {

        Set<EventKey> yamlKeys = properties.getConsumers().keySet();
        Set<EventKey> codeKeys = eventRegistry.keys();

        if (!yamlKeys.equals(codeKeys)) {
            throw new IllegalStateException("Event mismatch");
        }
    }
}