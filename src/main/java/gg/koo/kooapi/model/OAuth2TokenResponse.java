package gg.koo.kooapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OAuth2TokenResponse {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("expires_in")
    private long expiresIn;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("scope")
    private String scope;
}
