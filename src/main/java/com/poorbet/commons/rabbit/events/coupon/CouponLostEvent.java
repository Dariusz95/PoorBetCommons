package com.poorbet.commons.rabbit.events.coupon;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record CouponLostEvent(@NotNull UUID id) {
}
