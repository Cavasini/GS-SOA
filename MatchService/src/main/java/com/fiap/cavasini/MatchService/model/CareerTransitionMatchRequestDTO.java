package com.fiap.cavasini.MatchService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CareerTransitionMatchRequestDTO {

    private List<TransferableSkillDTO> transferableSkills;
    private List<String> skillGaps;
    private List<String> howToStart;
    private String narrativeSummary;

}
