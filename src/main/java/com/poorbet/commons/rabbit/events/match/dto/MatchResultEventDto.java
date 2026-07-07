package com.poorbet.commons.rabbit.events.match.dto;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record MatchResultEventDto(
        @NotNull UUID matchId,
        int homeGoals,
        int awayGoals
) {
}