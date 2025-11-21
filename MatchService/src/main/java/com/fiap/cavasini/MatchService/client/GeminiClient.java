package com.fiap.cavasini.MatchService.client;

import com.fiap.cavasini.MatchService.model.GeminiRequestDTO;
import com.fiap.cavasini.MatchService.model.GeminiResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;


@Component
public class GeminiClient {

    private final WebClient webClient;

    private final String geminiUrl = "https://generativelanguage.googleapis.com/v1beta";

    @Value("${gemini.api.key}")
    private String apiKey;

    public GeminiClient(WebClient.Builder builder){
        this.webClient = builder.baseUrl(geminiUrl).build();
    }

    public GeminiResponseDTO requestGemini(String text) {

        GeminiRequestDTO request = new GeminiRequestDTO(
                List.of(
                        new GeminiRequestDTO.Content(
                                List.of(
                                        new GeminiRequestDTO.Part(text)
                                )
                        )
                )
        );

        return webClient.post()
                .uri("/models/gemini-2.5-flash:generateContent")
                .header("x-goog-api-key", apiKey)
                .header("Content-Type", "application/json")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(GeminiResponseDTO.class)
                .block();
    }
}