package com.swissre.crypto;

import com.swissre.crypto.connector.CryptoCompareConnectorImpl;
import com.swissre.crypto.ex.FileFormatException;
import com.swissre.crypto.service.AssetPrinter;
import com.swissre.crypto.service.impl.AssetFileReaderImpl;
import com.swissre.crypto.service.impl.AssetPrinterImpl;
import com.swissre.crypto.service.impl.JsonPriceDeserializerImpl;

import java.io.IOException;

public class Main {

    // no need to catch these exceptions as the should come with meaningful messages themselves
    public static void main(String[] args) throws IOException, FileFormatException {
        try {
            AssetPrinter assetPrinter = new AssetPrinterImpl(new CryptoCompareConnectorImpl(new JsonPriceDeserializerImpl()), new AssetFileReaderImpl());
            assetPrinter.printAssets();
        } catch (IOException | FileFormatException ex) {
            System.err.println("There was an unexpected exception");
            System.exit(1);
        }
    }
}
