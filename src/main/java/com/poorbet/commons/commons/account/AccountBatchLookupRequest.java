package com.poorbet.commons.commons.account;

import jakarta.validation.constraints.NotEmpty;

import java.util.Set;
import java.util.UUID;

public record AccountBatchLookupRequest(
        @NotEmpty(message = "User ids cannot be empty")
        Set<UUID> userIds
) {}
