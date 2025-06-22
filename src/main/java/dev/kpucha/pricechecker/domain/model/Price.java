package dev.kpucha.pricechecker.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents a price in the system.
 */
public record Price(
        Integer brandId,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Integer priceList,
        Integer productId,
        Integer priority,
        BigDecimal price,
        String currency) {

}
