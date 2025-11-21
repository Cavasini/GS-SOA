package com.fiap.cavasini.MatchService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CareerOptionDTO {
    private String career;
    private String reason;
}
