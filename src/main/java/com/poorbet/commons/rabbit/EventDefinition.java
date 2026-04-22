package com.poorbet.commons.rabbit;


public record EventDefinition<T>(String exchange, String eventType, String version) {
    public String routingKey() {
        return eventType + "." + version;
    }
}