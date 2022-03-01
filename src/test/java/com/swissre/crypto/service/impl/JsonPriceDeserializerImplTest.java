package com.swissre.crypto.service.impl;

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
    void readPriceOk() {
        service.readPrice("{\"EUR\":13430.11}");
    }

    @Test
    void readPriceWithJsonFormatted() {
        service.readPrice("{\n\"EUR\":   13430.11  \n}   \n");
    }
}