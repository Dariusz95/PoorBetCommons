package com.poorbet.commons.rabbit;

import com.poorbet.commons.rabbit.events.coupon.CouponEvents;
import com.poorbet.commons.rabbit.events.match.MatchEvents;
import com.poorbet.commons.rabbit.events.user.UserEvents;
import com.poorbet.commons.rabbit.events.wallet.WalletEvents;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
public class EventRegistry {

    private final Map<EventKey, EventDefinition<?>> registry = Map.of(
            EventKey.WALLET_CREATED, WalletEvents.WALLET_CREATED,
            EventKey.WALLET_BALANCE_CHANGED, WalletEvents.WALLET_BALANCE_CHANGED,
            EventKey.USER_CREATED, UserEvents.USER_EVENTS,
            EventKey.MATCH_FINISHED, MatchEvents.MATCH_FINISHED,
            EventKey.COUPON_CREATION_FAILED, CouponEvents.COUPON_CREATION_FAILED,
            EventKey.COUPON_WON, CouponEvents.COUPON_WON,
            EventKey.COUPON_LOST, CouponEvents.COUPON_LOST
    );

    public EventDefinition<?> get(EventKey key) {
        return registry.get(key);
    }

    public Set<EventKey> keys() {
        return registry.keySet();
    }
}