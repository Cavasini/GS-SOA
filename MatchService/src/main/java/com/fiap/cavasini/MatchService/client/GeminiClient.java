package com.fiap.cavasini.MatchService.client;

import com.fiap.cavasini.MatchService.model.GeminiRequest;
import com.fiap.cavasini.MatchService.model.GeminiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;


@Component
@RequiredArgsConstructor
public class GeminiClient {

    private final WebClient geminiWebClient;

    @Value("${gemini.api.key}")
    private String apiKey;

    public GeminiResponse gerarResposta(String texto) {

        GeminiRequest request = new GeminiRequest(
                List.of(
                        new GeminiRequest.Content(
                                List.of(
                                        new GeminiRequest.Part(texto)
                                )
                        )
                )
        );

        return geminiWebClient.post()
                .uri("/models/gemini-2.5-flash:generateContent")
                .header("x-goog-api-key", apiKey)
                .header("Content-Type", "application/json")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(GeminiResponse.class)
                .block();
    }
}