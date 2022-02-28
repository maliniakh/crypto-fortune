package com.swissre.crypto.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JsonPriceDeserializerTest {

    JsonPriceDeserializer service;

    @BeforeEach
    void init() {
        // actually no need for @BeforeEach initialization (instead of plain class-level field assignment, but it won't hurt either
        service = new JsonPriceDeserializer();
    }

    @Test
    void readPriceOk() {
        service.readPrice("{\"EUR\":13430.11}");
    }

    @Test
    void readPriceWithJsonFormatted() {
        service.readPrice("{\n\"EUR\":   13430.11  \n}   \n");
    }
}