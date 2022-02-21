package gg.koo.kooapi.controller;

import gg.koo.kooapi.model.OAuth2TokenResponse;
import gg.koo.kooapi.service.OAuth2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/v1/tokens")
public class OAuth2Controller {

    private final OAuth2Service oAuth2Service;

    @Autowired
    public OAuth2Controller(OAuth2Service oAuth2Service) {
        this.oAuth2Service = oAuth2Service;
    }

    @GetMapping("/hello")
    public Mono<String> getTest(Model model) {
        return Mono.just("hello_test");
    }

    @GetMapping("/login")
    public Mono<OAuth2TokenResponse> getAccessToken(@RequestParam("code") String code) {
        Mono<OAuth2TokenResponse> result = oAuth2Service.getOAuth2Token(code);
        return result;

    }
}

