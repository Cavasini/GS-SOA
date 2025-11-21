package com.fiap.cavasini.MatchService.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.cavasini.MatchService.client.CareerClient;
import com.fiap.cavasini.MatchService.client.GeminiClient;
import com.fiap.cavasini.MatchService.model.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MatchService {

    private GeminiClient geminiClient;
    private CareerClient careerClient;
    public static final ObjectMapper objectMapper = new ObjectMapper();

    public CareerTransitionMatchRequestDTO SendToGeminiTransitionProfile(String request) {
        GeminiResponseDTO resp = geminiClient.requestGemini(request);

        return transformTransitionResponseBodyToJson(resp
                .getCandidates()
                .getFirst()
                .getContent()
                .getParts()
                .getFirst()
                .getText());
    }

    public CareerRecommendationResponseDTO SendToGeminiBeginnerProfile(String request) {
        GeminiResponseDTO resp = geminiClient.requestGemini(request);

        return transformBeginnerResponseBodyToJson(resp
                .getCandidates()
                .getFirst()
                .getContent()
                .getParts()
                .getFirst()
                .getText());
    }


    public String createPrompt(TransitionerRequestDTO request){
        StringBuilder prompt = new StringBuilder();

        String skillsBlock = request.getSkills().stream()
                .map(s -> String.format("    { \"name\": \"%s\", \"level\": %d }",
                        s.name(), s.level()))
                .collect(Collectors.joining(",\n"));

        prompt.append("Você receberá sempre um JSON no formato:\n")
                .append("\n")
                .append("{\n")
                .append("  \"currentArea\": \"").append(request.getCurrentArea()).append("\",\n")
                .append("  \"desiredCareer\": \"").append(request.getDesiredCareer()).append("\",\n")
                .append("  \"skills\": [\n")
                .append(skillsBlock) // Insere o bloco de skills
                .append("\n")
                .append("  ]\n")
                .append("}\n")
                .append("\n")
                .append("Sua função é:\n")
                .append("\n")
                .append("1. Analisar a área atual do usuário.\n")
                .append("2. Analisar a área desejada.\n")
                .append("3. Avaliar as skills que o usuário já possui e como elas se conectam com a carreira desejada.\n")
                .append("4. Explicar claramente:\n")
                .append("   - Como cada skill atual pode ajudar na transição.\n")
                .append("   - Quais gaps de skills existem.\n")
                .append("   - Quais skills devem ser aprendidas primeiro.\n")
                .append("5. Criar um plano inicial de migração com passos práticos (simples e aplicáveis).\n")
                .append("6. A resposta deve ser estruturada sempre assim:\n")
                .append("\n")
                .append("{\n")
                .append("  \"transferableSkills\": [\n")
                .append("    {\n")
                .append("      \"skill\": \"Nome da skill\",\n")
                .append("      \"reason\": \"Por que essa skill ajuda na nova área\"\n")
                .append("    }\n")
                .append("  ],\n")
                .append("  \"skillGaps\": [\n")
                .append("    \"lista das principais skills faltantes\"\n")
                .append("  ],\n")
                .append("  \"howToStart\": [\n")
                .append("    \"primeiro passo\",\n")
                .append("    \"segundo passo\",\n")
                .append("    \"terceiro passo\"\n")
                .append("  ],\n")
                .append("  \"narrativeSummary\": \"um texto explicando a transição de forma amigável\"\n")
                .append("}\n")
                .append("\n")
                .append("Sempre responda apenas neste formato JSON.");

        return prompt.toString();
    }


    public String buildCareerMatchPrompt(BeginnerCareerMatchRequestDTO request) {

        StringBuilder prompt = new StringBuilder();

        String areasJson = careerClient.requestCareerSummaries();

        String interestsBlock = request.getInterests().stream()
                .map(i -> "    \"" + i + "\"")
                .collect(Collectors.joining(",\n"));

        prompt.append("Você receberá sempre um JSON no formato:\n")
                .append("\n")
                .append("{\n")
                .append("  \"interests\": [\n")
                .append(interestsBlock)
                .append("\n")
                .append("  ],\n")
                .append("  \"areas\": ").append(areasJson).append("\n")
                .append("}\n")
                .append("\n")
                .append("Sua função é:\n")
                .append("\n")
                .append("1. Analisar os interesses do usuário.\n")
                .append("2. Analisar qual área e carreira (das que te forneci) melhor combina com o perfil.\n")
                .append("3. Verificar como os interesses do usuário se conectam com essa carreira.\n")
                .append("4. Explicar claramente:\n")
                .append("   - Por que essa área faz sentido.\n")
                .append("   - Por que essa carreira combina com o usuário.\n")
                .append("   - Como cada interesse reforça o encaixe.\n")
                .append("5. Caso haja carreiras alternativas relevantes, citá-las brevemente.\n")
                .append("6. A resposta deve ser sempre estruturada assim:\n")
                .append("\n")
                .append("{\n")
                .append("  \"recommendedArea\": \"nome da área\",\n")
                .append("  \"recommendedCareer\": \"nome da carreira\",\n")
                .append("  \"reason\": \"explicação clara do encaixe\",\n")
                .append("  \"interestConnections\": [\n")
                .append("    {\n")
                .append("      \"interest\": \"interesse do usuário\",\n")
                .append("      \"explanation\": \"como esse interesse se conecta com a carreira\"\n")
                .append("    }\n")
                .append("  ],\n")
                .append("  \"alternatives\": [\n")
                .append("    {\n")
                .append("      \"career\": \"carreira alternativa\",\n")
                .append("      \"reason\": \"por que essa outra carreira pode ser adequada\"\n")
                .append("    }\n")
                .append("  ],\n")
                .append("  \"summaryNarrative\": \"um texto amigável explicando a recomendação\"\n")
                .append("}\n")
                .append("\n")
                .append("Sempre responda apenas neste formato JSON.");

        return prompt.toString();
    }


    private static CareerTransitionMatchRequestDTO transformTransitionResponseBodyToJson(String json) {
        try {
            String cleanBody =cleanJson(json);
            return objectMapper.readValue(cleanBody, CareerTransitionMatchRequestDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter JSON para DTO: " + e.getMessage(), e);
        }
    }

    private static CareerRecommendationResponseDTO transformBeginnerResponseBodyToJson(String json) {
        try {
            String cleanBody =cleanJson(json);
            return objectMapper.readValue(cleanBody, CareerRecommendationResponseDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter JSON para DTO: " + e.getMessage(), e);
        }
    }

    private static String cleanJson(String raw) {
        return raw
                .replace("```json", "")
                .replace("```", "")
                .trim();
    }
}
