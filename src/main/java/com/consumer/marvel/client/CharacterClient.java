package com.consumer.marvel.client;

import com.consumer.marvel.dto.ApiResponse;
import com.consumer.marvel.encrypt.Md5Encrypt;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
@Slf4j
public class CharacterClient {

    @Value("${marvel.url}")
    private String marvelUrl;
    @Value("${marvel.characters}")
    private String characterApi;
    @Value("${marvel.publicKey}")
    private String publicKey;
    @Value("${marvel.privateKey}")
    private String privateKey;
    @Value("${marvel.ts}")
    private String timeStamp;


    public ApiResponse getCharacters() {
        ApiResponse appResponse = null;
        try {
            log.info("Consuming marvel characters");

            final String urlEndpoint = marvelUrl
                    + characterApi
                    + getAuthParams();

            final String jsonResponse = consumeEndpoint(urlEndpoint);

            final Gson gson = new Gson();
            appResponse = gson.fromJson(jsonResponse, ApiResponse.class);

        } catch (Exception e) {
            log.error("Error when try to consume marvel characters: ", e);
            e.printStackTrace();
        }
        return appResponse;
    }

    public ApiResponse getCharacterById(int id) {
        ApiResponse appResponse = null;
        try {
            log.info("Consuming marvel character by id: {}" ,id);

            final String urlEndpoint = marvelUrl
                    + characterApi
                    + "/" + id
                    + getAuthParams();

            final String jsonResponse = consumeEndpoint(urlEndpoint);

            final Gson gson = new Gson();
            appResponse = gson.fromJson(jsonResponse, ApiResponse.class);

        } catch (Exception e) {
            log.error("Error when try to consume marvel character by id <{}>: ", id, e);
            e.printStackTrace();
        }
        return appResponse;
    }

    private String getAuthParams() {
        final String hash = Md5Encrypt.getMD5(timeStamp + privateKey + publicKey);

        return "?ts=" + timeStamp
                + "&apikey=" + publicKey
                + "&hash=" + hash;
    }

    private String consumeEndpoint(String url) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            log.info(response.body());
            return response.body();
        } else {
            throw new RuntimeException("Failed to fetch character: " + response.statusCode());
        }
    }
}
