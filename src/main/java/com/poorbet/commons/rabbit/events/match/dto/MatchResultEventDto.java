package com.poorbet.commons.rabbit.events.match.dto;

import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class MatchResultEventDto {
    UUID id;
    int homeGoals;
    int awayGoals;
}