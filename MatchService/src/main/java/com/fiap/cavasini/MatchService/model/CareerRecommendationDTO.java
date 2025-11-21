package com.fiap.cavasini.MatchService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CareerRecommendationDTO {

    private String recommendedArea;
    private String recommendedCareer;
    private String reason;
    private List<InterestMatchDTO> interestConnections;
    private List<CareerOptionDTO> alternatives;
    private String summaryNarrative;

}
