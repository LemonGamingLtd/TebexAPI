package me.hugmanrique.tebexapi.utils;

import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import me.hugmanrique.tebexapi.exception.TebexException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

/**
 * Created by HugmanriqueMC. All Rights Reserved
 * The copy of this file may not be copied in any form without
 * the prior written permission of Hugo Manrique.
 *
 * @author Hugmanrique
 *         Spigot. Created the 14/05/2016.
 **/
public class JsonReader {
    public static JSONObject readJsonFromUrlGet(String direction, String key, boolean array) throws TebexException {
        return readJsonFromUrl(direction, key, array, null);
    }

    public static JSONObject readJsonFromUrlPost(String direction, String key, boolean array, @NotNull JSONObject post)
            throws TebexException {
        return readJsonFromUrl(direction, key, array, post);
    }

    private static JSONObject readJsonFromUrl(String direction, String key, boolean array, @Nullable JSONObject post)
            throws TebexException {
        try {
            URL url = new URL(direction);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("X-Tebex-Secret", key);

            if (post != null) {
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json");
                con.setDoOutput(true);

                try (OutputStream os = con.getOutputStream()) {
                    byte[] input = post.toString().getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);
                }
            }

            if (con.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
                String jsonText = readAll(reader);
                return new JSONObject((array ? "{main: " : "") + jsonText + (array ? "}" : ""));
            } else {
                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(con.getErrorStream(), StandardCharsets.UTF_8))) {
                    String jsonText = readAll(reader);
                    // no result
                    if (jsonText.equals("[]")) {
                        return null;
                    }
                    throw new TebexException(jsonText);
                }
            }
        } catch (IOException e) {
            throw new TebexException("Failed to read JSON from URL", e);
        }
    }

    private static String readAll(Reader reader) throws IOException {
        StringBuilder builder = new StringBuilder();
        int cp;
        while ((cp = reader.read()) != -1){
            builder.append((char) cp);
        }

        return builder.toString();
    }
}
