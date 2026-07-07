package com.poorbet.commons.rabbit.events.wallet;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

public record WalletBalanceChangedEvent(
        @NotNull UUID userId,
        @NotNull BigDecimal balance
) {
}
