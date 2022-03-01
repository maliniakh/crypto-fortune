package com.swissre.crypto.service;

import com.swissre.crypto.ex.ApiException;
import com.swissre.crypto.ex.FileFormatException;
import com.swissre.crypto.ex.JsonException;

import java.io.IOException;
import java.math.BigDecimal;

public interface AssetPrinter {
    BigDecimal printAssets() throws IOException, FileFormatException, ApiException, JsonException;
}
