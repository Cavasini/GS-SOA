package com.fiap.cavasini.MatchService.model;

import java.util.List;

public record CareerRecommendationResponseDTO(
        String recommendedArea,
        String recommendedCareer,
        String reason,
        List<InterestConnectionDTO> interestConnections,
        List<AlternativeDTO> alternatives,
        String summaryNarrative
) {

    public record InterestConnectionDTO(
            String interest,
            String explanation
    ) {}

    public record AlternativeDTO(
            String career,
            String reason
    ) {}
}
