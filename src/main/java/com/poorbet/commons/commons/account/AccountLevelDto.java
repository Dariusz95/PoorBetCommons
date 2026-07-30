package com.poorbet.commons.commons.account;

import java.util.UUID;

public record AccountLevelDto(
        UUID userId,
        int level
) {}
