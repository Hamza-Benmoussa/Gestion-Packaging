package com.leoni.packaging.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


import java.time.LocalDate;

@Data @AllArgsConstructor @Builder
public class StatisticsFilter {
    private LocalDate dateDebut;
    private LocalDate dateFin;

    public StatisticsFilter() {
        this.dateDebut = LocalDate.now();
        this.dateFin = LocalDate.now();
    }
}
