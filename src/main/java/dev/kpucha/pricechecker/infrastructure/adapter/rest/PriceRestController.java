package dev.kpucha.pricechecker.infrastructure.adapter.rest;

import org.springframework.web.bind.annotation.RestController;

import dev.kpucha.pricechecker.application.dto.PriceRequest;
import dev.kpucha.pricechecker.application.dto.PrioritizedPriceResponse;
import dev.kpucha.pricechecker.domain.exception.PriceNotFoundException;
import dev.kpucha.pricechecker.domain.port.input.GetPrioritizedPriceUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * REST controller for handling price-related requests.
 * Provides an endpoint to retrieve the prioritized price for a product and brand at a specific date.
 */
@RestController
public class PriceRestController {
    
    /**
     * Use case for retrieving the prioritized price.
     */
    private final GetPrioritizedPriceUseCase useCase;

    /**
     * Constructs a new PriceRestController with the given use case.
     *
     * @param useCase the use case for retrieving the prioritized price
     */
    public PriceRestController(GetPrioritizedPriceUseCase useCase) {
        this.useCase = useCase;
    }

    /**
     * Endpoint to retrieve the prioritized price for a product and brand at a specific date.
     * 
     * @param request the request containing application date, product ID, and brand ID
     * @return the prioritized price response
     * @throws PriceNotFoundException if no applicable price is found
     */
    @Operation(summary = "Get prioritized price for product and brand at given date",
                description = "Returns the applicable price with highest priority for a product and brand at a specific date")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the prioritized price"),
        @ApiResponse(responseCode = "400", description = "Invalid input parameters",
                     content = @Content(schema = @Schema(implementation = String.class),
                                        examples = @ExampleObject(value = "400 BAD_REQUEST Required request parameter 'brandId' is not present"))),
        @ApiResponse(responseCode = "404", description = "No applicable price found",
                     content = @Content(schema = @Schema(implementation = String.class),
                                        examples = @ExampleObject(value = "404 NOT_FOUND 'Prioritized price not found'")))
    })
    @PostMapping(path = "/prices/prioritized", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PrioritizedPriceResponse getPrioritizedPrice(@Valid @RequestBody PriceRequest request) {
        var result = useCase.getPrioritizedPrice(
            request.applicationDate(),
            request.productId(),
            request.brandId()
        );
        return new PrioritizedPriceResponse(
            result.productId(),
            result.brandId(),
            result.priceList(),
            result.startDate(),
            result.endDate(),
            result.price().doubleValue(),
            result.currency()
        );
    }
    
}
