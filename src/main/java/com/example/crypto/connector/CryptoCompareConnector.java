package com.example.crypto.connector;

import com.example.crypto.ex.ApiException;
import com.example.crypto.ex.JsonException;

import java.io.IOException;
import java.math.BigDecimal;

public interface CryptoCompareConnector {
    BigDecimal getPrice(String inSym, String outSym) throws IOException, ApiException, JsonException;
}
