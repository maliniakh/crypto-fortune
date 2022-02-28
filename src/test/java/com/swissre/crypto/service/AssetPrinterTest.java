package com.swissre.crypto.service;

import com.swissre.crypto.connector.CryptoCompareConnector;
import com.swissre.crypto.ex.FileFormatException;
import com.swissre.crypto.model.Asset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AssetPrinterTest {

    AssetPrinter assetPrinter;

    @BeforeEach
    public void init() {
        assetPrinter = new AssetPrinter();
    }

    @Test
    public void printAssetsOk() throws IOException, FileFormatException {
        assetPrinter.cryptoCompareConnector = new MockConnector();

        assetPrinter.assetFileReader = new MockReader();

        BigDecimal total = assetPrinter.printAssets();
        assertEquals(new BigDecimal(3 * 11 + 13 * 5 + 7 * 17), total);

        // emulating mockitito's verify
        assertEquals(1, ((MockReader)assetPrinter.assetFileReader).readFileCounter);
        assertEquals(3, ((MockConnector)assetPrinter.cryptoCompareConnector).getPriceCounter);
    }

    // technically it should be callled spy instead of mock
    private static class MockConnector extends CryptoCompareConnector {
        int getPriceCounter = 0;

        @Override
        public BigDecimal getPrice(String inSym, String outSym) throws IOException {
            getPriceCounter++;
            switch (inSym) {
                case "BTC":
                    return new BigDecimal(11);
                case "ETH":
                    return new BigDecimal(13);
                case "XRP":
                    return new BigDecimal(17);
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    private static class MockReader extends AssetFileReader {
        int readFileCounter = 0;

        @Override
        public List<Asset> readFile(String path) throws IOException, FileFormatException {
            readFileCounter++;
            return Arrays.asList(
                    new Asset("BTC", new BigDecimal(3)),
                    new Asset("ETH", new BigDecimal(5)),
                    new Asset("XRP", new BigDecimal(7)));
        }
    }
}