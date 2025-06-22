package dev.kpucha.pricechecker.infrastructure.adapter.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data repository for PriceEntity.
 */
public interface PriceJpaRepository extends JpaRepository<PriceEntity, Long> {

    /**
     * Finds all PriceEntity records by product ID and brand ID.
     *
     * @param productId the ID of the product
     * @param brandId   the ID of the brand
     * @return list of PriceEntity records matching the given product and brand IDs
     */
    List<PriceEntity> findByProductIdAndBrandId(Integer productId, Integer brandId);
}
