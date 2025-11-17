package com.fiap.cavasini.MatchService.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.cavasini.MatchService.client.GeminiClient;
import com.fiap.cavasini.MatchService.model.*;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class TesteService {

    private GeminiClient geminiClient;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public TesteService(GeminiClient geminiClient){
        this.geminiClient = geminiClient;
    }

    public CareerTransitionRequestDTO SendToGeminiTransitionProfile(String texto) {
        GeminiResponse resp = geminiClient.gerarResposta(texto);

        return transformTransitionResponseBodyToJson(resp
                .getCandidates()
                .get(0)
                .getContent()
                .getParts()
                .get(0)
                .getText());
    }

    public RecomendacaoCarreiraResponseDTO SendToGeminiBeginnerProfile(String texto) {
        GeminiResponse resp = geminiClient.gerarResposta(texto);

        return transformBeginnerResponseBodyToJson(resp
                .getCandidates()
                .get(0)
                .getContent()
                .getParts()
                .get(0)
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


    public String buildCareerMatchPrompt(CareerBeginnerRequestDTO request, String areasJson) {

        StringBuilder prompt = new StringBuilder();

        areasJson = "[\n" +
                "    {\n" +
                "        \"area\": \"Tecnologia\",\n" +
                "        \"careers\": [\n" +
                "            {\n" +
                "                \"id\": \"dev_frontend\",\n" +
                "                \"nome\": \"Desenvolvedor Frontend\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"data_scientist\",\n" +
                "                \"nome\": \"Cientista de Dados\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"data_engineer\",\n" +
                "                \"nome\": \"Engenheiro de Dados\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"devops_engineer\",\n" +
                "                \"nome\": \"Engenheiro DevOps\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"cybersec_analyst\",\n" +
                "                \"nome\": \"Analista de Cibersegurança\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"area\": \"Saúde\",\n" +
                "        \"careers\": [\n" +
                "            {\n" +
                "                \"id\": \"enfermeiro\",\n" +
                "                \"nome\": \"Enfermeiro(a)\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"fisioterapeuta\",\n" +
                "                \"nome\": \"Fisioterapeuta\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"psicologo_clinico\",\n" +
                "                \"nome\": \"Psicólogo(a) Clínico(a)\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"nutricionista\",\n" +
                "                \"nome\": \"Nutricionista\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"tecnico_radiologia\",\n" +
                "                \"nome\": \"Técnico de Radiologia\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"area\": \"Juridica\",\n" +
                "        \"careers\": [\n" +
                "            {\n" +
                "                \"id\": \"advogado_civel\",\n" +
                "                \"nome\": \"Advogado Cível\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"advogado_penal\",\n" +
                "                \"nome\": \"Advogado Penal\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"assistente_juridico\",\n" +
                "                \"nome\": \"Assistente Jurídico\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"analista_compliance\",\n" +
                "                \"nome\": \"Analista de Compliance\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"conciliador_mediador\",\n" +
                "                \"nome\": \"Conciliador/Mediador\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"area\": \"Marketing\",\n" +
                "        \"careers\": [\n" +
                "            {\n" +
                "                \"id\": \"analista_marketing_digital\",\n" +
                "                \"nome\": \"Analista de Marketing Digital\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"gestor_de_trafego\",\n" +
                "                \"nome\": \"Gestor de Tráfego\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"social_media\",\n" +
                "                \"nome\": \"Social Media\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"especialista_branding\",\n" +
                "                \"nome\": \"Especialista em Branding\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"copywriter\",\n" +
                "                \"nome\": \"Copywriter\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"area\": \"Engenharia\",\n" +
                "        \"careers\": [\n" +
                "            {\n" +
                "                \"id\": \"engenheiro_civil\",\n" +
                "                \"nome\": \"Engenheiro Civil\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"engenheiro_software\",\n" +
                "                \"nome\": \"Engenheiro de Software\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"engenheiro_eletricista\",\n" +
                "                \"nome\": \"Engenheiro Eletricista\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"engenheiro_producao\",\n" +
                "                \"nome\": \"Engenheiro de Produção\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"engenheiro_mecanico\",\n" +
                "                \"nome\": \"Engenheiro Mecânico\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "]";

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
                .append("  \"areaRecomendada\": \"nome da área\",\n")
                .append("  \"carreiraRecomendada\": \"nome da carreira\",\n")
                .append("  \"motivo\": \"explicação clara do encaixe\",\n")
                .append("  \"conexoesInteresses\": [\n")
                .append("    {\n")
                .append("      \"interesse\": \"interesse do usuário\",\n")
                .append("      \"explicacao\": \"como esse interesse se conecta com a carreira\"\n")
                .append("    }\n")
                .append("  ],\n")
                .append("  \"alternativas\": [\n")
                .append("    {\n")
                .append("      \"carreira\": \"carreira alternativa\",\n")
                .append("      \"motivo\": \"por que essa outra carreira pode ser adequada\"\n")
                .append("    }\n")
                .append("  ],\n")
                .append("  \"narrativaResumo\": \"um texto amigável explicando a recomendação\"\n")
                .append("}\n")
                .append("\n")
                .append("Sempre responda apenas neste formato JSON.");

        return prompt.toString();
    }


    private static CareerTransitionRequestDTO transformTransitionResponseBodyToJson(String json) {
        try {
            String cleanBody =cleanJson(json);
            return objectMapper.readValue(cleanBody, CareerTransitionRequestDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter JSON para DTO: " + e.getMessage(), e);
        }
    }

    private static RecomendacaoCarreiraResponseDTO transformBeginnerResponseBodyToJson(String json) {
        try {
            String cleanBody =cleanJson(json);
            return objectMapper.readValue(cleanBody, RecomendacaoCarreiraResponseDTO.class);
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
