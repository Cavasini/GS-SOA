package com.fiap.cavasini.MatchService.service;

import com.fiap.cavasini.MatchService.client.GeminiClient;
import com.fiap.cavasini.MatchService.model.GeminiResponse;
import org.springframework.stereotype.Service;

@Service
public class TesteService {

    private GeminiClient geminiClient;

    public TesteService(GeminiClient geminiClient){
        this.geminiClient = geminiClient;
    }

    public String perguntar(String texto) {
        GeminiResponse resp = geminiClient.gerarResposta(texto);

        return resp
                .getCandidates()
                .get(0)
                .getContent()
                .getParts()
                .get(0)
                .getText();
    }
}
