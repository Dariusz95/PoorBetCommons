package com.poorbet.commons.rabbit;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EventEnvelope<T>(
        @NotBlank String eventType,
        @NotBlank String version,
        @NotBlank String source,
        @NotNull @Valid T payload
) {
}
