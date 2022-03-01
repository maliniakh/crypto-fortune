package com.example.crypto.service.impl;

import com.example.crypto.ex.JsonException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JsonPriceDeserializerImplTest {

    JsonPriceDeserializerImpl service;

    @BeforeEach
    void init() {
        // actually no need for @BeforeEach initialization (instead of plain class-level field assignment, but it won't hurt either
        service = new JsonPriceDeserializerImpl();
    }

    @Test
    void readPriceOk() throws JsonException {
        service.readPrice("{\"EUR\":13430.11}");
    }

    @Test
    void readPriceWithJsonFormatted() throws JsonException {
        service.readPrice("{\n\"EUR\":   13430.11  \n}   \n");
    }

    @Test
    void malformedJson() {
        Assertions.assertThrows(JsonException.class, () -> {
            service.readPrice("{\"EUR\";13430.11}");
        });
    }

    @Test
    void wrongNumberFormatInJson() {
        Assertions.assertThrows(JsonException.class, () -> {
            service.readPrice("{\"USD\", \"not a number\"}");
        });
    }
}