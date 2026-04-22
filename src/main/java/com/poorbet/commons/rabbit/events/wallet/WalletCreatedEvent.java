package com.poorbet.commons.rabbit.events.wallet;

import java.util.UUID;

public record WalletCreatedEvent(
        UUID userId
) {
}
