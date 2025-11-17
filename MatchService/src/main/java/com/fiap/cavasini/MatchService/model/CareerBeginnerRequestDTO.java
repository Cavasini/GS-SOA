package com.fiap.cavasini.MatchService.model;

import java.util.List;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CareerBeginnerRequestDTO {

    @NotBlank(message = "O ID do usuário é obrigatório.")
    String userId;

    @NotEmpty(message = "A lista de interesses não pode ser vazia.")
    List<String> interests;


    public List<String> getInterests() {
        return interests;
    }
}
