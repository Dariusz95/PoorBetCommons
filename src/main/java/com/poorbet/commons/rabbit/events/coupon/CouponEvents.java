package com.poorbet.commons.rabbit.events.coupon;

import com.poorbet.commons.rabbit.EventDefinition;

public class CouponEvents {
    public static final EventDefinition<CouponCreationFailedEvent> COUPON_CREATION_FAILED =
            new EventDefinition<>(
                    "coupon.events",
                    "coupon.creation-failed",
                    "v1"
            );
    public static final EventDefinition<CouponWonEvent> COUPON_WON =
            new EventDefinition<>(
                    "coupon.events",
                    "coupon.won",
                    "v1"
            );

    public static final EventDefinition<CouponLostEvent> COUPON_LOST =
            new EventDefinition<>(
                    "coupon.events",
                    "coupon.lost",
                    "v1"
            );


}
