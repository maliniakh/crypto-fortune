package com.example.crypto.service;

import com.example.crypto.ex.JsonException;

import java.math.BigDecimal;

public interface JsonPriceDeserializer {
    BigDecimal readPrice(String json) throws JsonException;
}
