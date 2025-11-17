package com.fiap.cavasini.MatchService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecomendacaoCarreiraDTO {

    private String areaRecomendada;
    private String carreiraRecomendada;
    private String motivo;
    private List<ConexaoInteresseDTO> conexoesInteresses;
    private List<AlternativaCarreiraDTO> alternativas;
    private String narrativaResumo;

    public String getAreaRecomendada() {
        return areaRecomendada;
    }

    public void setAreaRecomendada(String areaRecomendada) {
        this.areaRecomendada = areaRecomendada;
    }

    public String getCarreiraRecomendada() {
        return carreiraRecomendada;
    }

    public void setCarreiraRecomendada(String carreiraRecomendada) {
        this.carreiraRecomendada = carreiraRecomendada;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public List<ConexaoInteresseDTO> getConexoesInteresses() {
        return conexoesInteresses;
    }

    public void setConexoesInteresses(List<ConexaoInteresseDTO> conexoesInteresses) {
        this.conexoesInteresses = conexoesInteresses;
    }

    public List<AlternativaCarreiraDTO> getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(List<AlternativaCarreiraDTO> alternativas) {
        this.alternativas = alternativas;
    }

    public String getNarrativaResumo() {
        return narrativaResumo;
    }

    public void setNarrativaResumo(String narrativaResumo) {
        this.narrativaResumo = narrativaResumo;
    }
}
