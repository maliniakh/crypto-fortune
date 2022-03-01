package com.example.crypto.service;

import com.example.crypto.ex.ApiException;
import com.example.crypto.ex.FileFormatException;
import com.example.crypto.ex.JsonException;

import java.io.IOException;
import java.math.BigDecimal;

public interface AssetPrinter {
    BigDecimal printAssets() throws IOException, FileFormatException, ApiException, JsonException;
}
