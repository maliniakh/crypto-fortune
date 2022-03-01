package com.swissre.crypto.service;

import com.swissre.crypto.ex.JsonException;

import java.math.BigDecimal;

public interface JsonPriceDeserializer {
    BigDecimal readPrice(String json) throws JsonException;
}
