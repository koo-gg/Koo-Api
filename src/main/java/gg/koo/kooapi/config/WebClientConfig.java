package gg.koo.kooapi.config;

import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient oAuth2WebClient() {

        HttpClient httpClient = HttpClient.create()
                .doOnConnected(
                        connection -> {
                            connection.addHandler(
                                    new ReadTimeoutHandler(
                                            2000, TimeUnit.SECONDS))
                                      .addHandler(
                                    new WriteTimeoutHandler(
                                            2000, TimeUnit.SECONDS));
                        }
                );

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .baseUrl("")
                .defaultHeader()
                .build()
    }
}
