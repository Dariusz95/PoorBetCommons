package com.poorbet.commons.commons.error;

import java.time.Instant;
import java.util.List;

public record ErrorResponse(
        String code,
        String message,
        Instant timestamp,
        String path,
        List<ValidationError> validationErrors
) {
    public ErrorResponse(String code, String message, Instant timestamp, String path) {
        this(code, message, timestamp, path, null);
    }

    public record ValidationError(String field, String message) {
    }
}
