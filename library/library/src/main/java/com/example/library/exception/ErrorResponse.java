package com.example.library.exception;

import java.time.Instant;

public record ErrorResponse (Instant occurrenceTime, String message) {
}
