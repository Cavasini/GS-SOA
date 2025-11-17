package com.fiap.cavasini.CareerService.model;

import java.util.List;

public class AreaCareersResponse {

    public String area;
    public List<CareerSummary> careers;

    public AreaCareersResponse(String area, List<CareerSummary> careers) {
        this.area = area;
        this.careers = careers;
    }
}
