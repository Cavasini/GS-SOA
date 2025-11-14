package com.fiap.cavasini.MatchService.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SkillLevelDTO(
        @NotBlank(message = "O nome da habilidade (skill name) é obrigatório.")
        String name,

        @NotNull(message = "O nível da habilidade é obrigatório.")
        @Min(value = 1, message = "O nível da habilidade deve ser no mínimo 1.")
        @Max(value = 3, message = "O nível da habilidade deve ser no máximo 3.")
        int level) {
}
