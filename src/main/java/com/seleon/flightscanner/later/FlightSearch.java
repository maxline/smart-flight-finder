package com.seleon.flightscanner.later;

import com.seleon.flightscanner.utils.FilesUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.StringJoiner;

public class FlightSearch {

    public static void main(String[] args) {
        try {
            String url = "https://flightera-flight-data.p.rapidapi.com/flights/search";
            //String url = "https://skyscanner44.p.rapidapi.com/search";

            Map<String, String> parameters = Map.of(
                    "adults", "1",
                    "origin", "WAW",
                    "destination", "BCN",
                    "departureDate", "2025-08-28",
                    "currency", "EUR"
            );

            StringJoiner sj = new StringJoiner("&");
            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                sj.add(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8) + "=" +
                        URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
            }

            URL obj = new URL(url + "?" + sj);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // Настройка запроса
            con.setRequestMethod("GET");
            con.setRequestProperty("X-RapidAPI-Key", "your_api_key");
            con.setRequestProperty("X-RapidAPI-Host", "skyscanner44.p.rapidapi.com");

            int responseCode = con.getResponseCode();
            System.out.println("Response Code : " + responseCode);

            // Чтение ответа
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println("Resonse from  API:");
            System.out.println(response);

            FilesUtil.save(response.toString(), "flight_search_output_01.json");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
