package com.fiap.cavasini.MatchService.model;

import java.util.List;

public class CareerTransitionRequestDTO {

    private List<TransferableSkillDTO> transferableSkills;
    private List<String> skillGaps;
    private List<String> howToStart;
    private String narrativeSummary;

    public List<TransferableSkillDTO> getTransferableSkills() {
        return transferableSkills;
    }

    public void setTransferableSkills(List<TransferableSkillDTO> transferableSkills) {
        this.transferableSkills = transferableSkills;
    }

    public List<String> getSkillGaps() {
        return skillGaps;
    }

    public void setSkillGaps(List<String> skillGaps) {
        this.skillGaps = skillGaps;
    }

    public List<String> getHowToStart() {
        return howToStart;
    }

    public void setHowToStart(List<String> howToStart) {
        this.howToStart = howToStart;
    }

    public String getNarrativeSummary() {
        return narrativeSummary;
    }

    public void setNarrativeSummary(String narrativeSummary) {
        this.narrativeSummary = narrativeSummary;
    }
}
