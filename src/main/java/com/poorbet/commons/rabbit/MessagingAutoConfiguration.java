package com.poorbet.commons.rabbit;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@AutoConfiguration
@EnableConfigurationProperties(MessagingProperties.class)
@ConditionalOnProperty(prefix = "messaging", name = "enabled", havingValue = "true", matchIfMissing = true)
public class MessagingAutoConfiguration {
}