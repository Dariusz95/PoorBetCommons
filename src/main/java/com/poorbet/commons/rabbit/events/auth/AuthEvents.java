package com.poorbet.commons.rabbit.events.auth;

import com.poorbet.commons.rabbit.EventDefinition;

public class AuthEvents {

    public static final EventDefinition<UserCreatedEvent> USER_EVENTS =
            new EventDefinition<>(
                    "auth.events",
                    "user.created",
                    "v1"
            );

    private AuthEvents() {
    }
}