package com.poorbet.commons.rabbit.events.match;

import com.poorbet.commons.rabbit.events.match.dto.MatchResultEventDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record MatchesFinishedEvent(
        @NotNull @NotEmpty @Valid List<MatchResultEventDto> results
) {
}
