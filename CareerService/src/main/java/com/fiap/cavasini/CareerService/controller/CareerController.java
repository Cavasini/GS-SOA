package com.fiap.cavasini.CareerService.controller;

import com.fiap.cavasini.CareerService.model.AreaCareersResponse;
import com.fiap.cavasini.CareerService.model.Career;
import com.fiap.cavasini.CareerService.service.CareerLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/careers")
public class CareerController {

    private CareerLoader careerLoader;

    public CareerController(CareerLoader careerLoader){
        this.careerLoader = careerLoader;
    }

    @GetMapping
    public ResponseEntity<List<Career>> getCareerFullData(){
       List<Career> careers = careerLoader.loadCareers();

        if (careers == null || careers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

       return ResponseEntity.ok(careers);
    }


    @GetMapping("/summaries")
    public ResponseEntity<List<AreaCareersResponse>> getSummaries(){
        List<AreaCareersResponse> areaCareersList = careerLoader.loadAreasAndCareers();
        return ResponseEntity.ok(areaCareersList);
    }
}
