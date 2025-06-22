package dev.kpucha.pricechecker.infrastructure.adapter.rest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import dev.kpucha.pricechecker.domain.exception.PriceNotFoundException;

/**
 * Global exception handler for price-related controllers.
 * This class can be used to handle exceptions and provide custom responses.
 */
@RestControllerAdvice
public class PriceControllerAdvice {

    /**
     * Handles MethodArgumentNotValidException and returns a 400 Bad Request response.
     *
     * @param ex the exception containing validation errors
     * @return ResponseEntity with error details
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("error", "Bad Request");

        ex.getBindingResult().getAllErrors().forEach(error -> {
            errorResponse.put(error.getObjectName(), error.getDefaultMessage());
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
    
    /**
     * Handles PriceNotFoundException and returns a 404 Not Found response.
     *
     * @return ResponseEntity with error details
     */
    @ExceptionHandler(PriceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handlePriceNotFound() {
        Map<String, Object> error = Map.of(
            "timestamp", LocalDateTime.now(),
            "status", HttpStatus.NOT_FOUND.value(),
            "error", "Not Found",
            "message", "Prioritized price not found for the given parameters"
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
