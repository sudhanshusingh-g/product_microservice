package org.example.productservices.services;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UserServiceClient {
    private final WebClient webClient;
    public UserServiceClient(WebClient webClient) {
        this.webClient = webClient;
    }
    public boolean validateToken(String token) {
        try {
            return Boolean.TRUE.equals(webClient.get()
                    .uri("http://localhost:8001/api/token/validate")
                    .header("Authorization", "Bearer " + token)
                    .retrieve()
                            .onStatus(HttpStatusCode::is4xxClientError,response-> Mono.error(new RuntimeException("Invalid token")))
                            .onStatus(HttpStatusCode::is5xxServerError,response->Mono.error(new RuntimeException("SERVER_ERROR")))
                    .bodyToMono(Boolean.class)
                    .block());
        }
        catch (HttpClientErrorException | HttpServerErrorException e) {
            return false;
        }
    }
}
