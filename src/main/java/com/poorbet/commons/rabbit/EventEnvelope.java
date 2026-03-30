package com.poorbet.commons.rabbit;

import java.time.Instant;

public record EventEnvelope<T>(
        String eventType,
        String eventVersion,
        Instant occurredAt,
        String sourceService,
        String correlationId,
        T payload
) {
}
