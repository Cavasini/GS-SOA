package com.fiap.cavasini.CareerService.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.cavasini.CareerService.model.Career;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CareerLoader {

    private final ObjectMapper mapper = new ObjectMapper();

    private final String filePath = "src/main/resources/data.json";

    public List<Career> loadCareers() {
        try {
            Map<String, List<Career>> data = mapper.readValue(
                    new File(filePath),
                    new TypeReference<Map<String, List<Career>>>() {}
            );

            List<Career> result = new ArrayList<>();

            for (Map.Entry<String, List<Career>> entry : data.entrySet()) {
                String area = entry.getKey();

                for (Career c : entry.getValue()) {
                    c.area = area;
                    result.add(c);
                }
            }

            return result;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar JSON: " + e.getMessage(), e);
        }
    }
}
