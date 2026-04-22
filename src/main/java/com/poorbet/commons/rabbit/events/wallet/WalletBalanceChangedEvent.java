package com.poorbet.commons.rabbit.events.wallet;

import java.math.BigDecimal;
import java.util.UUID;

public record WalletBalanceChangedEvent(
        UUID userId,
        BigDecimal balance
) {
}
