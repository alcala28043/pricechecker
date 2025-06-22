package dev.kpucha.pricechecker.domain.port.input;

import java.time.LocalDateTime;

import dev.kpucha.pricechecker.domain.exception.PriceNotFoundException;
import dev.kpucha.pricechecker.domain.model.Price;

/**
 * Input port for retrieve the highest priority price for a product and brand
 * at a specific date.
 * If multiple prices are valid at the given date, the one with the highest
 * priority is returned.
 */
public interface GetPrioritizedPriceUseCase {

    /**
     * Retrieves the price with the highest priority for a given product and brand
     * at a specific application date.
     * 
     * @param applicationDate the date and time for being checked
     * @param productId       the ID of the product
     * @param brandId         the ID of the brand
     * @return the price for the given parameters, or null if no price is found
     * @throws PriceNotFoundException if no applicable price is found
     */
    Price getPrioritizedPrice(LocalDateTime applicationDate, Integer productId, Integer brandId);

}
