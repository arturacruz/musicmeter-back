package br.edu.insper.musicmeter.common.spotify;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.MediaType;

import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;


public class SpotifyRequester {
    public static String token;
    public static LocalDateTime lastAcquired;
    public static final RestClient client = RestClient.create();

    private static boolean isTokenExpired() {
        if(lastAcquired == null) {
            return true;
        }
        return LocalDateTime.now().minusHours(1).isAfter(lastAcquired);
    }

    private static void reacquireTokenIfNeeded() {
        if(isTokenExpired()) {
            getNewToken();
        }
    }

    private static JsonNode makeRequest(String path) {
        return client.get()
                .uri(path)
                .headers(headers -> headers.setBearerAuth(token))
                .retrieve()
                .body(JsonNode.class);
    }

    private static JsonNode search(String query, String type) {
        reacquireTokenIfNeeded();

        String path = "https://api.spotify.com/v1/search?type=" + type + "&market=BR&q=" + query;
        return makeRequest(path);
    }

    public static JsonNode searchMusic(String query) {
        return search(query, "track");
    }

    public static JsonNode searchAlbum(String query) {
        return search(query, "album");
    }

    private static void getNewToken() {
        String clientId = "c94580c82ac444ad89ccc41e81ce4884";
        String clientSecret = "eb2fceeffcb74abda49b62c0e41aeb2e";

        String formData = "grant_type=client_credentials" +
                "&client_id=" + clientId +
                "&client_secret=" + clientSecret;
        TokenDTO response = client.post()
                .uri("https://accounts.spotify.com/api/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(formData)
                .retrieve()
                .body(TokenDTO.class);

        if(response == null || response.accessToken() == null) {
            return;
        }
        token = response.accessToken();
        lastAcquired = LocalDateTime.now();
    }

    public static JsonNode getAlbum(String id) {
        reacquireTokenIfNeeded();
        String path = "https://api.spotify.com/v1/albums/" + id;
        return makeRequest(path);
    }


}
