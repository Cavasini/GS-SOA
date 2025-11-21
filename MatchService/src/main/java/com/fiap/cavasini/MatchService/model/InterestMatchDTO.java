package com.fiap.cavasini.MatchService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class InterestMatchDTO {
    private String interest;
    private String explanation;
}
