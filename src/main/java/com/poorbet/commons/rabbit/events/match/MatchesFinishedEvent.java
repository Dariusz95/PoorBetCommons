package com.poorbet.commons.rabbit.events.match;

import com.poorbet.commons.rabbit.events.match.dto.MatchResultEventDto;

import java.util.List;

public record MatchesFinishedEvent(
        List<MatchResultEventDto> results
) {
}
