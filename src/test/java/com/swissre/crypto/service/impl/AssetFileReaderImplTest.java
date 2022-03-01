package com.swissre.crypto.service.impl;

import com.swissre.crypto.ex.FileFormatException;
import com.swissre.crypto.model.Asset;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.NoSuchFileException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AssetFileReaderImplTest {

    AssetFileReaderImpl assetFileReader;

    @BeforeEach
    void setUp() {
        assetFileReader = new AssetFileReaderImpl();
    }

    @Test
    void readFileOk() throws IOException, FileFormatException {
        List<Asset> assets = assetFileReader.readFile("src/test/resources/ok_file.txt");

        assertEquals(3, assets.size());

        assertEquals("BTC", assets.get(0).getSymbol());
        assertEquals(new BigDecimal("10.47"), assets.get(0).getAmount());

        assertEquals("ETH", assets.get(1).getSymbol());
        assertEquals(new BigDecimal("5"), assets.get(1).getAmount());

        assertEquals("XRP", assets.get(2).getSymbol());
        assertEquals(new BigDecimal("2000"), assets.get(2).getAmount());
    }

    @Test
    void readFileWhiteCharactersAreHandledCorrectly() throws IOException, FileFormatException {
        List<Asset> assets = assetFileReader.readFile("src/test/resources/extra_white_chars.txt");

        assertEquals(3, assets.size());

        assertEquals("BTC", assets.get(0).getSymbol());
        assertEquals(new BigDecimal("10.47"), assets.get(0).getAmount());

        assertEquals("ETH", assets.get(1).getSymbol());
        assertEquals(new BigDecimal("5"), assets.get(1).getAmount());

        assertEquals("XRP", assets.get(2).getSymbol());
        assertEquals(new BigDecimal("2000"), assets.get(2).getAmount());
    }

    @Test
    void readFileDuplicatedEntries() {
        Assertions.assertThrows(FileFormatException.class, () -> {
            assetFileReader.readFile("src/test/resources/duplicated_entries.txt");
        });
    }

    @Test
    void readFileWrongFormatException() {
        Assertions.assertThrows(FileFormatException.class, () -> {
            assetFileReader.readFile("src/test/resources/wrong_format.txt");
        });
    }

    @Test
    void readFileNoSuchFileException() {
        Assertions.assertThrows(NoSuchFileException.class, () -> {
            assetFileReader.readFile("no_such_file.txt");
        });
    }

}