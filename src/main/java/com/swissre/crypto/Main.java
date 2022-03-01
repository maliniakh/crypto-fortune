package com.swissre.crypto;

import com.swissre.crypto.connector.impl.CryptoCompareConnectorImpl;
import com.swissre.crypto.ex.ApiException;
import com.swissre.crypto.ex.FileFormatException;
import com.swissre.crypto.ex.JsonException;
import com.swissre.crypto.service.AssetPrinter;
import com.swissre.crypto.service.impl.AssetFileReaderImpl;
import com.swissre.crypto.service.impl.AssetPrinterImpl;
import com.swissre.crypto.service.impl.JsonPriceDeserializerImpl;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            AssetPrinter assetPrinter = new AssetPrinterImpl(new CryptoCompareConnectorImpl(new JsonPriceDeserializerImpl()), new AssetFileReaderImpl());
            assetPrinter.printAssets();
        } catch (IOException ex) {
            System.err.println("There was an IO exception: " + ex);
            System.exit(1);
        } catch (FileFormatException ex) {
            System.err.println("Input file is malformed: " + ex);
            System.exit(1);
        } catch (ApiException ex) {
            System.err.println("There was external API exception: " + ex);
            System.exit(1);
        } catch (JsonException ex) {
            System.err.println("There was exception while parsing JSON from API: " + ex);
            System.exit(1);
        }
    }
}
