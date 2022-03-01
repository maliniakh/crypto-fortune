package com.swissre.crypto.connector.impl;

import com.swissre.crypto.connector.CryptoCompareConnector;
import com.swissre.crypto.ex.ApiException;
import com.swissre.crypto.ex.JsonException;
import com.swissre.crypto.service.JsonPriceDeserializer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;

public class CryptoCompareConnectorImpl implements CryptoCompareConnector {

    JsonPriceDeserializer jsonPriceDeserializer;

    private final static String URL_TEMPLATE = "https://min-api.cryptocompare.com/data/price?fsym={0}&tsyms={1}";

    public CryptoCompareConnectorImpl(JsonPriceDeserializer jsonPriceDeserializer) {
        this.jsonPriceDeserializer = jsonPriceDeserializer;
    }

    @Override
    public BigDecimal getPrice(String inSym, String outSym) throws IOException, ApiException, JsonException {
        URL url;

        url = getUrl(inSym, outSym);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        int responseCode = connection.getResponseCode();
        String response = getResponse(connection);

        if (responseCode != HttpURLConnection.HTTP_OK) {
            // IOException is definitely not the best suited exception here, but I think it's just
            // pragmatic here not to use create a separate one here
            throw new ApiException("Unexpected response code from API (" + responseCode + ") - (body: " + response + ")");
        }

        return jsonPriceDeserializer.readPrice(response);
    }

    private static URL getUrl(String inSym, String outSym) {
        String urlStr = MessageFormat.format(URL_TEMPLATE, inSym, outSym);
        try {
            return new URL(urlStr);
        } catch (MalformedURLException e) {
            throw new IllegalStateException(e);
        }
    }

    private static String getResponse(HttpURLConnection connection) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }
}
