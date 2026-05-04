package com.poorbet.commons.rabbit.events.coupon;

import java.math.BigDecimal;
import java.util.UUID;

public record CouponWonEvent(UUID couponId, UUID reservationId, UUID userId, BigDecimal amount) {
}
