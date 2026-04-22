package com.poorbet.commons.rabbit.events.user;

import com.poorbet.commons.rabbit.EventDefinition;

public class UserEvents {

    public static final EventDefinition<UserCreatedEvent> USER_EVENTS =
            new EventDefinition<>(
                    "user.events",
                    "user.created",
                    "v1"
            );

    private UserEvents() {
    }
}