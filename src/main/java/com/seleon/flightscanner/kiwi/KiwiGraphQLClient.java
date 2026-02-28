package com.seleon.flightscanner.kiwi;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class KiwiGraphQLClient {

    public HttpResponse<String> callApi(String url, String query) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        //todo try to change cookies and token to anonymous
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(15))
                .header("sec-ch-ua-platform", "\"macOS\"")
                .header("Referer", "https://www.kiwi.com/")
                .header("kw-skypicker-visitor-uniqid", "d60d848f-bc33-4c35-9031-259dbb9c7160")
                .header("sec-ch-ua", "\"Not)A;Brand\";v=\"8\", \"Chromium\";v=\"138\", \"Google Chrome\";v=\"138\"")
                .header("sec-ch-ua-mobile", "?0")
                .header("kw-umbrella-token", "2fc19af350fa0a392e3dfd6b28fce531d1f7df0a039943830373342bf2d329fc")
                .header("kw-x-rand-id", "b9119a97")
                .header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/138.0.0.0 Safari/537.36")
                .header("content-type", "application/json")
                .header("Cookie", "_cfuvid=mJCHkGmwHwanDcuA1flWVDuYX5XG6KoTfUFLEr2bDCI-1752304902763-0.0.1.1-604800000")
                .POST(HttpRequest.BodyPublishers.ofString(query))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }

}
