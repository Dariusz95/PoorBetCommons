package com.poorbet.commons.rabbit.events.wallet;

import com.poorbet.commons.rabbit.EventDefinition;

public class WalletEvents {

    public static final EventDefinition<WalletCreatedEvent> WALLET_CREATED =
            new EventDefinition<>(
                    "wallet.events",
                    "wallet.created",
                    "v1"
            );

    public static final EventDefinition<WalletBalanceChangedEvent> WALLET_BALANCE_CHANGED =
            new EventDefinition<>(
                    "wallet.events",
                    "wallet.balance-changed",
                    "v1"
            );

    private WalletEvents() {
    }
}