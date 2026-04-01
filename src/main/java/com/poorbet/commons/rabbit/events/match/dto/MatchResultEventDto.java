package com.poorbet.commons.rabbit.events.match.dto;

import java.util.UUID;

public record MatchResultEventDto(
        UUID matchId,
        int homeGoals,
        int awayGoals
) {
}