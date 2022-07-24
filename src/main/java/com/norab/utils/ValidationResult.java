package com.norab.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ValidationResult(
    @JsonProperty("is_valid")
    boolean isValid
) {
}
