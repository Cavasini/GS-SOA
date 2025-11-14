package com.fiap.cavasini.CareerService.controller;

import com.fiap.cavasini.CareerService.model.Career;
import com.fiap.cavasini.CareerService.service.CareerLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CareerController {

    private CareerLoader careerLoader;

    public CareerController(CareerLoader careerLoader){
        this.careerLoader = careerLoader;
    }

    @GetMapping
    public List<Career> teste(){
       List<Career> careers = careerLoader.loadCareers();
       return careers;
    }
}
