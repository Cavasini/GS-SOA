package com.fiap.cavasini.MatchService.model;

import java.util.List;

import jakarta.validation.constraints.*;

public class BeginnerRequestDTO{

    @NotBlank(message = "O ID do usuário é obrigatório.")
    String userId;

    @NotEmpty(message = "A lista de interesses não pode ser vazia.")
    List<String> interests;


    public BeginnerRequestDTO(String userId, List<String> interests) {
        this.userId = userId;
        this.interests = interests;
    }

    public List<String> getInterests() {
        return interests;
    }
}
