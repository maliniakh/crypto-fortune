package com.swissre.crypto.service;

import com.swissre.crypto.connector.CryptoCompareConnector;
import com.swissre.crypto.ex.FileFormatException;
import com.swissre.crypto.model.Asset;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static java.math.RoundingMode.HALF_EVEN;

public class AssetPrinter {

    CryptoCompareConnector cryptoCompareConnector = new CryptoCompareConnector();

    AssetFileReader assetFileReader = new AssetFileReader();

    private static final String FILENAME = "bobs_crypto.txt";

    /**
     * Currency, which the assets are converted to
     */
    private static final String SYMBOL = "EUR";

    /**
     * @throws IOException Error when reading the file or unexpected response from the API.
     * @throws FileFormatException Malformed file.
     * @return Sum of the assets' values.
     */
    public BigDecimal printAssets() throws IOException, FileFormatException {
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
        return String.format("%-7s %10s", symbol, amount.setScale(2, HALF_EVEN).toString());
    }
}
