package dev.kpucha.pricechecker.infrastructure.adapter.persistence;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import dev.kpucha.pricechecker.domain.model.Price;
import dev.kpucha.pricechecker.domain.port.output.PriceRepositoryPort;

/**
 * H2 implementation of the PriceRepositoryPort that interacts with the
 * PriceJpaRepository to retrieve prices from the database.
 */
@Repository
public class H2PriceAdapter implements PriceRepositoryPort {

    /**
     * Repository for accessing PriceEntity data.
     */
    private final PriceJpaRepository priceJpaRepository;

    /**
     * Constructs a new H2PriceAdapter with the given PriceJpaRepository.
     *
     * @param priceJpaRepository the repository to access price data
     */
    public H2PriceAdapter(PriceJpaRepository priceJpaRepository) {
        this.priceJpaRepository = priceJpaRepository;
    }

    /**
     * Finds all prices applicable to the given product and brand.
     *
     * @param productId the ID of the product
     * @param brandId   the ID of the brand
     * @return list of prices available for the given product and brand
     */
    @Override
    public List<Price> findPricesByProductIdAndBrandId(Integer productId, Integer brandId) {
        return priceJpaRepository.findByProductIdAndBrandId(productId, brandId)
            .stream()
            .map(this::toDomainModel)
            .collect(Collectors.toList());
    }

    /**
     * Converts a PriceEntity to a Price domain model.
     *
     * @param entity the PriceEntity to convert
     * @return the corresponding Price domain model
     */
    private Price toDomainModel(PriceEntity entity) {
        return new Price(
            entity.getBrandId(),
            entity.getStartDate(),
            entity.getEndDate(),
            entity.getPriceList(),
            entity.getProductId(),
            entity.getPriority(),
            entity.getPrice(),
            entity.getCurrency()
        );
    }
}