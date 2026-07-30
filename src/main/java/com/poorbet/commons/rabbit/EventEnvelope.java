package com.poorbet.commons.rabbit;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record EventEnvelope<T>(
        @NotNull UUID eventId,
        @NotBlank String eventType,
        @NotBlank String version,
        @NotBlank String source,
        @NotNull @Valid T payload
) {
}
