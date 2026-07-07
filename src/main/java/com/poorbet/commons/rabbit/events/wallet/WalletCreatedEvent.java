package com.poorbet.commons.rabbit.events.wallet;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record WalletCreatedEvent(
        @NotNull UUID userId
) {
}
