package com.swissre.crypto.model;

import java.math.BigDecimal;

/**
 * Represents a single entry from the txt file
 */
public class Asset {

    private final String symbol;

    private final BigDecimal amount;

    public Asset(String symbol, BigDecimal amount) {
        this.symbol = symbol;
        this.amount = amount;
    }

    public String getSymbol() {
        return symbol;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
