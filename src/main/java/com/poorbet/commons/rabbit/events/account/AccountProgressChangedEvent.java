package com.poorbet.commons.rabbit.events.account;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record AccountProgressChangedEvent(
        @NotNull UUID userId,
        int level,
        long currentExp,
        Long requiredExpForNextLevel,
        int winBonusPercent
) {
}
