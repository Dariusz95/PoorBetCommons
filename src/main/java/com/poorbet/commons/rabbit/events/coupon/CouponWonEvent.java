package com.poorbet.commons.rabbit.events.coupon;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

public record CouponWonEvent(
        @NotNull UUID couponId,
        @NotNull UUID reservationId,
        @NotNull UUID userId,
        @NotNull @DecimalMin("0.01") BigDecimal amount
) {
}
