package com.fiap.cavasini.MatchService.model;

import java.util.List;

public record RecomendacaoCarreiraResponseDTO(
        String areaRecomendada,
        String carreiraRecomendada,
        String motivo,
        List<ConexaoInteresseDTO> conexoesInteresses,
        List<AlternativaDTO> alternativas,
        String narrativaResumo
) {

    public record ConexaoInteresseDTO(
            String interesse,
            String explicacao
    ) {}

    public record AlternativaDTO(
            String carreira,
            String motivo
    ) {}
}
