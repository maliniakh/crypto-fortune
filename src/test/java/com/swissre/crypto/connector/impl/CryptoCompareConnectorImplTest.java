package com.swissre.crypto.connector.impl;

import com.swissre.crypto.ex.ApiException;
import com.swissre.crypto.ex.JsonException;
import com.swissre.crypto.service.impl.JsonPriceDeserializerImpl;
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