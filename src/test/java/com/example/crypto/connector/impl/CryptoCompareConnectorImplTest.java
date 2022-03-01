package com.example.crypto.connector.impl;

import com.example.crypto.ex.ApiException;
import com.example.crypto.ex.JsonException;
import com.example.crypto.service.impl.JsonPriceDeserializerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * It's an intergration test, but it indirectly tests reading from the API
 */
class CryptoCompareConnectorImplTest {


    CryptoCompareConnectorImpl cryptoCompareConnector;

    @BeforeEach
    void setup() {
        cryptoCompareConnector = new CryptoCompareConnectorImpl(new JsonPriceDeserializerImpl());
    }

    @Test
    void getPriceDoge() throws IOException, ApiException, JsonException {
        BigDecimal price = cryptoCompareConnector.getPrice("DOGE", "USD");

        assertNotNull(price);
    }
}