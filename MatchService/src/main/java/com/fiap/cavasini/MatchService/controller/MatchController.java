package com.fiap.cavasini.MatchService.controller;

import com.fiap.cavasini.MatchService.model.BeginnerRequestDTO;
import com.fiap.cavasini.MatchService.model.TransitionerRequestDTO;
import com.fiap.cavasini.MatchService.service.TesteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/match")
public class MatchController {

    private TesteService testeService;

    public MatchController(TesteService testeService){
        this.testeService = testeService;
    }

    @PostMapping("/transition")
    public ResponseEntity MatchTransitionCareer(@Valid @RequestBody TransitionerRequestDTO request){
        String result  = testeService.perguntar("quanto é 1 + 1");
        return ResponseEntity.ok(result);

    }

    @PostMapping("/beginner")
    public ResponseEntity MatchAspirantCareer(@Valid @RequestBody BeginnerRequestDTO request){

        return ResponseEntity.ok(request);

    }
}
