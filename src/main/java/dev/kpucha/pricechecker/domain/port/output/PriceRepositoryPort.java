package dev.kpucha.pricechecker.domain.port.output;

import java.util.List;

import dev.kpucha.pricechecker.domain.model.Price;

/**
 * Output port used by domain to retrieve prices from external sources.
 */
public interface PriceRepositoryPort {

    /**
     * Finds all prices applicable to the given product and brand.
     *
     * @param productId the ID of the product
     * @param brandId   the ID of the brand
     * @return list of prices available for the given product and brand
     */
    List<Price> findPricesByProductIdAndBrandId(Integer productId, Integer brandId);
}
