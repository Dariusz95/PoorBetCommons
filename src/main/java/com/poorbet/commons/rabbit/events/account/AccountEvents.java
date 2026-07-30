package com.poorbet.commons.rabbit.events.account;

import com.poorbet.commons.rabbit.EventDefinition;

public class AccountEvents {

    public static final EventDefinition<AccountProgressChangedEvent> ACCOUNT_PROGRESS_CHANGED =
            new EventDefinition<>(
                    "account.events",
                    "account.progress-changed",
                    "v1"
            );

    private AccountEvents() {
    }
}
