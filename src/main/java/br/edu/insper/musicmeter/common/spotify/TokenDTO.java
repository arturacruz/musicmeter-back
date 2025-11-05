package br.edu.insper.musicmeter.common.spotify;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TokenDTO(
        @JsonProperty(value = "access_token")
        String accessToken,
        @JsonProperty(value = "token_type")
        String tokenType,
        @JsonProperty(value = "expires_in")
        int expiresIn
) {
}
