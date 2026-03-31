package com.poorbet.commons.rabbit;

public record EventDefinition<T>(String eventType, String version, String routingKey) {

}