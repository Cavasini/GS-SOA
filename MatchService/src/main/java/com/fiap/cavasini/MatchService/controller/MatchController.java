package com.fiap.cavasini.MatchService.controller;

import com.fiap.cavasini.MatchService.client.CareerClient;
import com.fiap.cavasini.MatchService.model.*;
import com.fiap.cavasini.MatchService.service.MatchService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/matches")
@AllArgsConstructor
public class MatchController {

    private MatchService matchService;

    @PostMapping("/transition")
    public ResponseEntity<CareerTransitionMatchRequestDTO> transitionCareerMatch(@Valid @RequestBody TransitionerRequestDTO request){
        String prompt = matchService.createPrompt(request);
        CareerTransitionMatchRequestDTO response = matchService.SendToGeminiTransitionProfile(prompt);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/beginner")
    public ResponseEntity<CareerRecommendationResponseDTO> beginnerCareerMatch(@Valid @RequestBody BeginnerCareerMatchRequestDTO request){
        String prompt = matchService.buildCareerMatchPrompt(request);
        CareerRecommendationResponseDTO response = matchService.SendToGeminiBeginnerProfile(prompt);
        return ResponseEntity.ok(response);

    }
}
