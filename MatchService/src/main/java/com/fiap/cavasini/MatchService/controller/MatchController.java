package com.fiap.cavasini.MatchService.controller;

import com.fiap.cavasini.MatchService.model.CareerBeginnerRequestDTO;
import com.fiap.cavasini.MatchService.model.CareerTransitionRequestDTO;
import com.fiap.cavasini.MatchService.model.RecomendacaoCarreiraResponseDTO;
import com.fiap.cavasini.MatchService.model.TransitionerRequestDTO;
import com.fiap.cavasini.MatchService.service.TesteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/match")
@AllArgsConstructor
public class MatchController {

    private TesteService testeService;

    @PostMapping("/transition")
    public ResponseEntity MatchTransitionCareer(@Valid @RequestBody TransitionerRequestDTO request){
        String prompt = testeService.createPrompt(request);
        CareerTransitionRequestDTO response = testeService.SendToGeminiTransitionProfile(prompt);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/beginner")
    public ResponseEntity MatchAspirantCareer(@Valid @RequestBody CareerBeginnerRequestDTO request){
        String prompt = testeService.buildCareerMatchPrompt(request, null);
        RecomendacaoCarreiraResponseDTO response = testeService.SendToGeminiBeginnerProfile(prompt);
        return ResponseEntity.ok(response);

    }
}
