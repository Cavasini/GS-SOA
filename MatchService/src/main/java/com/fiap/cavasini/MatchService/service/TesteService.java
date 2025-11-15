package com.fiap.cavasini.MatchService.service;

import com.fiap.cavasini.MatchService.client.GeminiClient;
import com.fiap.cavasini.MatchService.model.GeminiResponse;
import com.fiap.cavasini.MatchService.model.TransitionerRequestDTO;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

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

//    "Você receberá sempre um JSON no formato:\n" +
//            "\n" +
//            "{\n" +
//            "  \"currentArea\": \"" + request.getCurrentArea() + "\",\n" +
//            "  \"desiredCareer\": \"" + request.getDesiredCareer()  + "\",\n" +
//            "  \"skills\": [\n" +
//            "    { \"name\": \"Excel\", \"level\": 4 },\n" +
//            "    { \"name\": \"SQL\", \"level\": 2 },\n" +
//            "    { \"name\": \"Google Analytics\", \"level\": 3 }\n" +
//            "  ]\n" +
//            "}\n" +
//            "\n" +
//            "Sua função é:\n" +
//            "\n" +
//            "1. Analisar a área atual do usuário.\n" +
//            "2. Analisar a área desejada.\n" +
//            "3. Avaliar as skills que o usuário já possui e como elas se conectam com a carreira desejada.\n" +
//            "4. Explicar claramente:\n" +
//            "   - Como cada skill atual pode ajudar na transição.\n" +
//            "   - Quais gaps de skills existem.\n" +
//            "   - Quais skills devem ser aprendidas primeiro.\n" +
//            "5. Criar um plano inicial de migração com passos práticos (simples e aplicáveis).\n" +
//            "6. A resposta deve ser estruturada sempre assim:\n" +
//            "\n" +
//            "{\n" +
//            "  \"transferableSkills\": [\n" +
//            "    {\n" +
//            "      \"skill\": \"Nome da skill\",\n" +
//            "      \"reason\": \"Por que essa skill ajuda na nova área\"\n" +
//            "    }\n" +
//            "  ],\n" +
//            "  \"skillGaps\": [\n" +
//            "    \"lista das principais skills faltantes\"\n" +
//            "  ],\n" +
//            "  \"howToStart\": [\n" +
//            "    \"primeiro passo\",\n" +
//            "    \"segundo passo\",\n" +
//            "    \"terceiro passo\"\n" +
//            "  ],\n" +
//            "  \"narrativeSummary\": \"um texto explicando a transição de forma amigável\"\n" +
//            "}\n" +
//            "\n" +
//            "Sempre responda apenas neste formato JSON."



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
}
