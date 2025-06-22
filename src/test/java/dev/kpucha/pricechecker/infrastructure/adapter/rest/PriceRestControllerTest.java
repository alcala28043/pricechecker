package dev.kpucha.pricechecker.infrastructure.adapter.rest;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.kpucha.pricechecker.application.dto.PriceRequest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Integration tests for the PriceRestController.
 * This class tests the prioritized price retrieval functionality.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class PriceRestControllerTest {

    /**
     * MockMvc instance for testing the REST controller.
     * This allows us to perform requests and verify responses.
     */
    @Autowired
    private MockMvc mockMvc;

    /** 
     * ObjectMapper instance for converting objects to JSON.
     * This is used to serialize the request body in tests.
     */
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Tests the retrieval of prioritized prices at 10 AM on June 14th 2020.
     */
    @Test
    void test1_at_10am_on_14th() throws Exception {
        var request = new PriceRequest(LocalDateTime.of(2020, 6, 14, 10, 0), 35455, 1);

        mockMvc.perform(post("/prices/prioritized")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.price").value(35.50));
    }

    /**
     * Tests the retrieval of prioritized prices at 4 PM on June 14th 2020.
     * Verifies that the correct price list and price are returned.
     * @throws Exception
     */
    @Test
    void test2_at_4pm_on_14th() throws Exception {
        var request = new PriceRequest(LocalDateTime.of(2020, 6, 14, 16, 0), 35455, 1);

        mockMvc.perform(post("/prices/prioritized")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(2))
                .andExpect(jsonPath("$.price").value(25.45));
    }

    /**
     * Tests the retrieval of prioritized prices at 9 PM on June 14th 2020.
     * Verifies that the correct price list and price are returned.
     * @throws Exception
     */
    @Test
    void test3_at_9pm_on_14th() throws Exception {
        var request = new PriceRequest(LocalDateTime.of(2020, 6, 14, 21, 0), 35455, 1);

        mockMvc.perform(post("/prices/prioritized")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.price").value(35.50));
    }

    /**
     * Tests the retrieval of prioritized prices at 10 AM on June 15th 2020.
     * Verifies that the correct price list and price are returned.
     * @throws Exception
     */
    @Test
    void test4_at_10am_on_15th() throws Exception {
        var request = new PriceRequest(LocalDateTime.of(2020, 6, 15, 10, 0), 35455, 1);

        mockMvc.perform(post("/prices/prioritized")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(3))
                .andExpect(jsonPath("$.price").value(30.50));
    }

    /**
     * Tests the retrieval of prioritized prices at 10 PM on June 16th 2020.
     * Verifies that the correct price list and price are returned.
     * @throws Exception
     */
    @Test
    void test5_at_10pm_on_16th() throws Exception {
        var request = new PriceRequest(LocalDateTime.of(2020, 6, 16, 22, 0), 35455, 1);

        mockMvc.perform(post("/prices/prioritized")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(4))
                .andExpect(jsonPath("$.price").value(38.95));
    }
}
