package com.poorbet.commons.rabbit;

public record EventEnvelope<T>(
        String eventType,
        String version,
        String source,
        T payload
) {
}
