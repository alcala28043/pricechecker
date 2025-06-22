package dev.kpucha.pricechecker.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.kpucha.pricechecker.domain.exception.PriceNotFoundException;
import dev.kpucha.pricechecker.domain.model.Price;
import dev.kpucha.pricechecker.domain.port.output.PriceRepositoryPort;

/**
 * Unit tests for the PriceService class.
 * Tests the retrieval of prioritized prices based on application date, product ID, and brand ID.
 */
public class PriceServiceTest {

    /**
     * Mocked port to access price data from persistence.
     */
    private PriceRepositoryPort priceRepositoryPort;
    /**
     * Service under test that retrieves the prioritized price.
     */
    private PriceService priceService;

    /**
     * Sets up the test environment before each test.
     * Initializes the mocked PriceRepositoryPort and the PriceService instance.
     */
    @BeforeEach
    void setUp() {
        priceRepositoryPort = mock(PriceRepositoryPort.class);
        priceService = new PriceService(priceRepositoryPort);
    }

    /**
     * Tests the retrieval of a prioritized price when there is one matching price.
     * Verifies that the correct price is returned based on the application date.
     */
    @Test
    void test_getPrioritizedPrice_whenOneMatch_returnsCorrectPrice() {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);

        List<Price> prices = List.of(
                new Price(1,
                        LocalDateTime.of(2020, 6, 14, 0, 0),
                        LocalDateTime.of(2020, 12, 31, 23, 59),
                        1, 35455, 0,
                        BigDecimal.valueOf(35.50), "EUR"));

        when(priceRepositoryPort.findPricesByProductIdAndBrandId(35455, 1)).thenReturn(prices);

        Price result = priceService.getPrioritizedPrice(applicationDate, 35455, 1);

        assertNotNull(result);
        assertEquals(1, result.priceList());
        assertEquals(BigDecimal.valueOf(35.50), result.price());
    }

    /**
     * Tests the retrieval of a prioritized price when there are multiple matching prices.
     * Verifies that the price with the highest priority is returned.
     */
    @Test
    void test_getPrioritizedPrice_whenMultipleMatches_returnsHighestPriority() {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 16, 0);

        List<Price> prices = List.of(
                new Price(1,
                        LocalDateTime.of(2020, 6, 14, 0, 0),
                        LocalDateTime.of(2020, 12, 31, 23, 59),
                        1, 35455, 0,
                        BigDecimal.valueOf(35.50), "EUR"),

                new Price(1,
                        LocalDateTime.of(2020, 6, 14, 15, 0),
                        LocalDateTime.of(2020, 6, 14, 18, 30),
                        2, 35455, 1,
                        BigDecimal.valueOf(25.45), "EUR"));

        when(priceRepositoryPort.findPricesByProductIdAndBrandId(35455, 1)).thenReturn(prices);

        Price result = priceService.getPrioritizedPrice(applicationDate, 35455, 1);

        assertNotNull(result);
        assertEquals(2, result.priceList());
        assertEquals(BigDecimal.valueOf(25.45), result.price());
    }

    /**
     * Tests the retrieval of a prioritized price when there are no matching prices.
     * Verifies that a PriceNotFoundException is thrown.
     */
    @Test
    void test_getPrioritizedPrice_whenNoMatches_throwsException() {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 5, 14, 10, 0);

        List<Price> prices = List.of(
                new Price(1,
                        LocalDateTime.of(2020, 6, 14, 0, 0),
                        LocalDateTime.of(2020, 12, 31, 23, 59),
                        1, 35455, 0,
                        BigDecimal.valueOf(35.50), "EUR"));

        when(priceRepositoryPort.findPricesByProductIdAndBrandId(35455, 1)).thenReturn(prices);

        assertThrows(PriceNotFoundException.class, () -> {
            priceService.getPrioritizedPrice(applicationDate, 35455, 1);
        });
    }

}
