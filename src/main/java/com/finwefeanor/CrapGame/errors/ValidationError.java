package com.finwefeanor.CrapGame.errors;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents a validation error with details about the offending field and the associated error message.
 */
@Data
@AllArgsConstructor
public class ValidationError {
    private String field;
    private String message;
}
