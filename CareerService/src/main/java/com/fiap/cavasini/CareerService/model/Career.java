package com.fiap.cavasini.CareerService.model;

import java.util.List;

public class Career {
    public String _id;
    public String area;
    public String nome;
    public String descricao;
    public String rotina;

    public List<String> requisitos;
    public List<String> habilidades;
    public List<String> ferramentas;
    public List<String> tendencias;
    public List<String> empresasQueContratam;

    public List<Influenciador> influenciadores;
    public List<ConteudoYoutube> conteudosYoutube;
    public List<Comunidade> comunidades;
    public List<RoadmapItem> roadmap;
}



