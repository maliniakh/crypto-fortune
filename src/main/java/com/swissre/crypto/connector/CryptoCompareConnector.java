package com.swissre.crypto.connector;

import com.swissre.crypto.ex.ApiException;
import com.swissre.crypto.ex.JsonException;

import java.io.IOException;
import java.math.BigDecimal;

public interface CryptoCompareConnector {
    BigDecimal getPrice(String inSym, String outSym) throws IOException, ApiException, JsonException;
}
