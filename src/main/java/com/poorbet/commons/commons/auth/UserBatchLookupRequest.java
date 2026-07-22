package com.poorbet.commons.commons.auth;

import jakarta.validation.constraints.NotEmpty;

import java.util.Set;
import java.util.UUID;

public record UserBatchLookupRequest(
        @NotEmpty(message = "User ids cannot be empty")
        Set<UUID> ids
) {}