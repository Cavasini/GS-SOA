package com.fiap.cavasini.MatchService.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fiap.cavasini.MatchService.model.GeminiResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static com.fiap.cavasini.MatchService.service.MatchService.objectMapper;

@Component
public class CareerClient {

    private final WebClient webClient;

    private final String careerUrl = "http://localhost:8081/api";

    public CareerClient(WebClient.Builder builder){
        this.webClient = builder.baseUrl(careerUrl).build();
    }

    public String requestCareerSummaries(){
        try {
            Mono<String> result = webClient.get()
                    .uri("/v1/careers/summaries")
                    .retrieve()
                    .bodyToMono(Object.class)
                    .map(obj -> {
                        try {
                            return objectMapper.writeValueAsString(obj);
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }
                    });
            return result.block();
        } catch (Exception e) {
            System.out.println("Houve um erro durante a pesquisa: " + e.getMessage());
            return defaultResponse();
        }
    }

    private String defaultResponse(){
        return "[\n" +
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

    }

}
