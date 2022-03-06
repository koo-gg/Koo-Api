package gg.koo.kooapi.service;

import gg.koo.kooapi.model.OAuth2TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service("oAuth2Service")
@Component
public class OAuth2Service {

    @Autowired
    private final WebClient oAuth2WebClient;

    @Value("${discord.oAuth2.client_id}")
    private String clientId;

    @Value("${discord.oAuth2.client_secret}")
    private String clientSecret;

    @Value("${discord.oAuth2.redirect_uri}")
    private String redirectUri;

    @Autowired
    public OAuth2Service(WebClient oAuth2WebClient) {
        this.oAuth2WebClient = oAuth2WebClient;
    }

    public Mono<OAuth2TokenResponse> getOAuth2Token(String code) {
        return oAuth2WebClient.post()         // POST method
                .uri("/oauth2/token")     // baseUrl 이후 uri
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(
                        "client_id", clientId)
                        .with("client_secret", clientSecret)
                        .with("grant_type", "authorization_code")
                        .with("code", code)
                        .with("redirect_uri", redirectUri))
                .retrieve()
                .bodyToMono(OAuth2TokenResponse.class);
    }
}

