package com.swissre.crypto.service;

import com.swissre.crypto.ex.FileFormatException;
import com.swissre.crypto.model.Asset;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AssetFileReader {
    /**
     * Reads Bob's file. White characters and empty lines are ignored
     * @param path Path to file
     * @return List Read entries in the same order as in the file.
     * @throws IOException Error reading the file
     * @throws FileFormatException File has wrong format.
     */
    public List<Asset> readFile(String path) throws IOException, FileFormatException {
        // one might prefer to use Files.lines which returns stream, but unfortunately java streams
        // are somehow limited (e.g. can't throw a checked exception from within)
        List<String> lines = Files.readAllLines(Paths.get(path));

        // trimming the lines and removing empty ones
        lines = lines.stream()
                .map(l -> l.replaceAll("\\s",""))
                .filter(l -> !l.isEmpty())
                .collect(Collectors.toList());

        List<Asset> assets = new ArrayList<>();

        for(String line : lines) {
            String[] split = line.split("=");
            if(split.length != 2) {
                throw new FileFormatException();
            }

            String symbol = split[0].trim();
            BigDecimal amount = new BigDecimal(split[1].trim());

            assets.add(new Asset(symbol, amount));
        }

        List<String> symbols = assets.stream().map(Asset::getSymbol).collect(Collectors.toList());
        Set<String> duplicated = symbols.stream().filter(s -> Collections.frequency(symbols, s) > 1).collect(Collectors.toSet());
        if(duplicated.size() > 0) {
            throw new FileFormatException("Duplicated symbols in the file - " + duplicated);
        }

        return assets;
    }
}
