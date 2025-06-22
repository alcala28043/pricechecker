package dev.kpucha.pricechecker.application.dto;

import java.time.LocalDateTime;

public record PrioritizedPriceResponse(
        Integer productId,
        Integer brandId,
        Integer priceList,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Double price,
        String currency) {

}
