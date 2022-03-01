package com.swissre.crypto.service;

import com.swissre.crypto.ex.FileFormatException;
import com.swissre.crypto.model.Asset;

import java.io.IOException;
import java.util.List;

public interface AssetFileReader {
    List<Asset> readFile(String path) throws IOException, FileFormatException;
}
