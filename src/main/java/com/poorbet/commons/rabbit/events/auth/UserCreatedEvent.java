package com.poorbet.commons.rabbit.events.auth;

import java.util.UUID;

public record UserCreatedEvent(
        UUID userId
) {
}
