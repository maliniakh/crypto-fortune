package com.example.crypto;

import com.example.crypto.connector.impl.CryptoCompareConnectorImpl;
import com.example.crypto.ex.FileFormatException;
import com.example.crypto.service.impl.AssetFileReaderImpl;
import com.example.crypto.service.impl.AssetPrinterImpl;
import com.example.crypto.service.impl.JsonPriceDeserializerImpl;
import com.example.crypto.ex.ApiException;
import com.example.crypto.ex.JsonException;
import com.example.crypto.service.AssetPrinter;

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
