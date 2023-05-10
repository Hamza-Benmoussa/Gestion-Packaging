package com.leoni.packaging.dto;

import com.leoni.packaging.model.Route;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
public class RouteCablesCountDto {
    private Route route;
    private List<LineCablesCountDto> lines;
    private long totalCables;
}


