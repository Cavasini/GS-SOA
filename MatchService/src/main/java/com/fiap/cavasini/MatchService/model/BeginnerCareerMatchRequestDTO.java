package com.fiap.cavasini.MatchService.model;

import java.util.List;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BeginnerCareerMatchRequestDTO {

    @NotBlank(message = "O ID do usuário é obrigatório.")
    String userId;

    @NotEmpty(message = "A lista de interesses não pode ser vazia.")
    List<String> interests;

}
