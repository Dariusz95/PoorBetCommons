package com.poorbet.commons.commons.auth;

import java.util.Map;
import java.util.UUID;

public record UserBatchLookupResponse(
        Map<UUID, UserDto> users
) {}