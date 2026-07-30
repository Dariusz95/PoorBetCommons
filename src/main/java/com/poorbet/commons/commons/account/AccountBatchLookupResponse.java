package com.poorbet.commons.commons.account;

import java.util.Map;
import java.util.UUID;

public record AccountBatchLookupResponse(
        Map<UUID, AccountLevelDto> accounts
) {}
