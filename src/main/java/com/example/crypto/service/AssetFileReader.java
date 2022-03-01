package com.example.crypto.service;

import com.example.crypto.ex.FileFormatException;
import com.example.crypto.model.Asset;

import java.io.IOException;
import java.util.List;

public interface AssetFileReader {
    List<Asset> readFile(String path) throws IOException, FileFormatException;
}
