package gg.koo.kooapi.controller;

import gg.koo.kooapi.model.OAuth2TokenResponse;
import gg.koo.kooapi.service.OAuth2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;

@RestController
public class OAuth2Controller {

    private final OAuth2Service oAuth2Service;

    @Autowired
    public OAuth2Controller(OAuth2Service oAuth2Service) {
        this.oAuth2Service = oAuth2Service;
    }

    @GetMapping("/about")
    public Mono<String> getTest() {
        return Mono.just("About");
    }

    @GetMapping("/login")
    public Mono<OAuth2TokenResponse> getAccessToken(@RequestParam("code") String code) {
        Mono<OAuth2TokenResponse> result = oAuth2Service.getOAuth2Token(code);
        return result;

    }
}

