package com.fiap.cavasini.MatchService.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class TransitionerRequestDTO{

    @NotBlank(message = "O ID do usuário é obrigatório.")
    String userId;

    @NotBlank(message = "A área atual (currentArea) é obrigatória.")
    @NotNull(message = "A área atual (currentArea) é obrigatória.")
    String currentArea;

    @NotBlank(message = "A carreira desejada (desiredCareer) é obrigatória.")
    @NotNull(message = "A carreira desejada (desiredCareer) é obrigatória.")
    String desiredCareer;

    @NotEmpty(message = "A lista de habilidades (skills) não pode ser vazia.")
    List<SkillLevelDTO> skills;

    public TransitionerRequestDTO(String userId, String currentArea, String desiredCareer, List<SkillLevelDTO> skills) {
        this.userId = userId;
        this.currentArea = currentArea;
        this.desiredCareer = desiredCareer;
        this.skills = skills;
    }

    public String getCurrentArea() {
        return currentArea;
    }

    public String getDesiredCareer() {
        return desiredCareer;
    }

    public List<SkillLevelDTO> getSkills() {
        return skills;
    }
}
