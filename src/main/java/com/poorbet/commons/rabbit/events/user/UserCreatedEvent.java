package com.poorbet.commons.rabbit.events.user;

import java.util.UUID;

public record UserCreatedEvent(
        UUID userId
) {
}
