package com.poorbet.commons.rabbit.events.auth;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record UserCreatedEvent(
        @NotNull UUID userId
) {
}
