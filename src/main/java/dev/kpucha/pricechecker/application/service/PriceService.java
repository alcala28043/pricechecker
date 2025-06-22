package dev.kpucha.pricechecker.application.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.kpucha.pricechecker.domain.exception.PriceNotFoundException;
import dev.kpucha.pricechecker.domain.model.Price;
import dev.kpucha.pricechecker.domain.port.input.GetPrioritizedPriceUseCase;
import dev.kpucha.pricechecker.domain.port.output.PriceRepositoryPort;

/**
 * Implementation of the GetPrioritizedPriceUseCase that retrieves the
 * applicable price with highest priority.
 */
@Service
public class PriceService implements GetPrioritizedPriceUseCase {

    /**
     * Port to access price data from persistence.
     */
    private final PriceRepositoryPort priceRepositoryPort;

    /**
     * Constructs a new PriceService with the given repository port.
     *
     * @param priceRepositoryPort the port to access price data from persistence
     */
    public PriceService(PriceRepositoryPort priceRepositoryPort) {
        this.priceRepositoryPort = priceRepositoryPort;
    }

    /**
     * Retrieves the prioritized price applicable at the specified date for the
     * given product and brand.
     *
     * If multiple prices are valid at the application date,
     * the one with the highest priority is selected.
     *
     * @param applicationDate the date to check applicability
     * @param productId       the ID of the product
     * @param brandId         the ID of the brand
     * @return the applicable price with the highest priority
     * @throws PriceNotFoundException if no applicable price is found
     */
    @Override
    public Price getPrioritizedPrice(LocalDateTime applicationDate, Integer productId, Integer brandId) {
        List<Price> applicablePrices = priceRepositoryPort.findPricesByProductIdAndBrandId(productId, brandId);

        return applicablePrices.stream()
                .filter(p -> !applicationDate.isBefore(p.startDate()) && !applicationDate.isAfter(p.endDate()))
                .max(Comparator.comparingInt(Price::priority))
                .orElseThrow(() -> new PriceNotFoundException("No applicable price found for the given parameters"));
    }
}
