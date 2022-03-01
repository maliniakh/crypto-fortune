package com.swissre.crypto.service.impl;

import com.swissre.crypto.ex.JsonException;
import com.swissre.crypto.service.JsonPriceDeserializer;

import java.math.BigDecimal;

public class JsonPriceDeserializerImpl implements JsonPriceDeserializer {

    /**
     * Parses provided response json.
     * @param json Json with single price field.
     * @return Parsed price.
     */
    @Override
    public BigDecimal readPrice(String json) throws JsonException {
        try {
            // removing all white characters in case the json is formatted (as it is not guaranteed/specified by the api)
            // reassinging method parameters might be considered a code smell but I don't see anything wrong with it here
            json = json.replaceAll("\\s", "");
            String priceStr = json.split(":")[1].replaceFirst("}", "");

            return new BigDecimal(priceStr);
        } catch (Exception ex) {
            // catching unchecked exceptions too, but it's the most straightforward approach here
            throw new JsonException("JSON: " + json, ex);
        }
    }
}
