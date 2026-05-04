package com.poorbet.commons.rabbit.events.coupon;

import java.util.UUID;

public record CouponCreationFailedEvent(UUID reservationId) {
}
