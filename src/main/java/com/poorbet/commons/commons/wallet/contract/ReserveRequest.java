package com.poorbet.commons.commons.wallet.contract;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record ReserveRequest(
        @NotNull
        UUID reservationId,

        @NotNull
        @DecimalMin(value = "1.00")
        BigDecimal amount
) {
}