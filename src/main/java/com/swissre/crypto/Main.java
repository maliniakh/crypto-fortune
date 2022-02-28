package com.swissre.crypto;

import com.swissre.crypto.ex.FileFormatException;
import com.swissre.crypto.service.AssetPrinter;

import java.io.IOException;

public class Main {

    // no need to catch these exceptions as the should come with meaningful messages themselves
    public static void main(String[] args) throws IOException, FileFormatException {
        new AssetPrinter().printAssets();
    }
}
