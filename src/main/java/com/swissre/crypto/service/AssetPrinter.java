package com.swissre.crypto.service;

import com.swissre.crypto.ex.FileFormatException;

import java.io.IOException;
import java.math.BigDecimal;

public interface AssetPrinter {
    BigDecimal printAssets() throws IOException, FileFormatException;
}
