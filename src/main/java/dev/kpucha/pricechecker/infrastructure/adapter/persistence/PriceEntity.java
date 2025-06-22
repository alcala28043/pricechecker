package dev.kpucha.pricechecker.infrastructure.adapter.persistence;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a price entity in the database.
 */
@Entity
@Table(name = "PRICES")
@Getter
@Setter
public class PriceEntity {
    
    @Id
    private Long id;

    private Integer brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer priceList;
    private Integer productId;
    private Integer priority;
    private BigDecimal price;
    private String currency;
    private LocalDateTime lastUpdate;
    private String lastUpdateBy;
}
