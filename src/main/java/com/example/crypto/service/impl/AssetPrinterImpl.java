package com.example.crypto.service.impl;

import com.example.crypto.connector.CryptoCompareConnector;
import com.example.crypto.ex.FileFormatException;
import com.example.crypto.model.Asset;
import com.example.crypto.ex.ApiException;
import com.example.crypto.ex.JsonException;
import com.example.crypto.service.AssetFileReader;
import com.example.crypto.service.AssetPrinter;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static java.math.RoundingMode.HALF_EVEN;

public class AssetPrinterImpl implements AssetPrinter {

    CryptoCompareConnector cryptoCompareConnector;

    AssetFileReader assetFileReader;

    private static final String FILENAME = "bobs_crypto.txt";

    /**
     * Currency, which the assets are converted to
     */
    private static final String SYMBOL = "EUR";

    public AssetPrinterImpl(CryptoCompareConnector cryptoCompareConnector, AssetFileReader assetFileReader) {
        this.cryptoCompareConnector = cryptoCompareConnector;
        this.assetFileReader = assetFileReader;
    }

    /**
     * @throws IOException Error when reading the file or unexpected response from the API.
     * @throws FileFormatException Malformed file.
     * @return Sum of the assets' values.
     */
    @Override
    public BigDecimal printAssets() throws IOException, FileFormatException, ApiException, JsonException {
        List<Asset> assets = assetFileReader.readFile(FILENAME);

        BigDecimal sum = BigDecimal.valueOf(0);
        for (Asset asset : assets) {
            BigDecimal price = cryptoCompareConnector.getPrice(asset.getSymbol(), SYMBOL);
            BigDecimal value = asset.getAmount().multiply(price);
            sum = sum.add(value);

            System.out.println(format(asset.getSymbol(), value));
        }

        System.out.println("\n" + format("TOTAL", sum));

        // returning the sum is unncessery here, but it enables to test it
        // without introducting yet another and artificial class collaborator
        return sum;
    }

    private String format(String symbol, BigDecimal amount) {
        // using %f instead of %s for bigDecimal didn't work for me as expected
        return String.format("%-7s %10s", symbol, amount.setScale(2, HALF_EVEN));
    }
}
