package com.swissre.crypto.service;

import java.math.BigDecimal;

public interface JsonPriceDeserializer {
    BigDecimal readPrice(String json);
}
