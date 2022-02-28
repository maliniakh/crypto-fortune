package com.swissre.crypto.service;

import java.math.BigDecimal;

public class JsonPriceDeserializer {

    /**
     * Parses provided response json.
     * @param json Json with single price field.
     * @return Parsed price.
     */
    public BigDecimal readPrice(String json) {
        // the json is not validated explicitly and silently it's assumed it's correct
        // validating it would require extra checks, which we wouldn't be able to handle for the purpose of this
        // program either way
        // the only means of checking the response is by the http status code

        // removing all white characters in case the json is formatted (as it is not guaranteed/specified by the api)
        // reassinging method parameters might be considered a code smell but I don't see anything wrong with it here
        json = json.replaceAll("\\s","");
        String priceStr = json.split(":")[1].replaceFirst("}", "");
        return new BigDecimal(priceStr);
    }
}
