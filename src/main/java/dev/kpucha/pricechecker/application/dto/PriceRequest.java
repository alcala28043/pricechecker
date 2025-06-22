package dev.kpucha.pricechecker.application.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Represents a request for price information.
 */
public record PriceRequest(
        @NotNull(message = "Application date is required") LocalDateTime applicationDate,
        @NotNull(message = "Product ID is required") @Positive(message = "Product ID must be positive") Integer productId,
        @NotNull(message = "Brand ID is required") @Positive(message = "Brand ID must be positive") Integer brandId) {
}
