package com.poorbet.commons.rabbit.events.match;

import com.poorbet.commons.rabbit.EventDefinition;

public class MatchEvents {

    public static final EventDefinition<MatchesFinishedEvent> MATCH_FINISHED =
            new EventDefinition<>(
                    "match.events",
                    "match.finished",
                    "v1"
            );

    private MatchEvents() {
    }
}