package com.leoni.packaging.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


import java.time.LocalDate;

@Data @AllArgsConstructor @Builder
public class StatisticsFilter {
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String route;
    private boolean ok;
    private boolean rework;
    private boolean ll;
    private boolean rl;
    private boolean ov;
    private boolean ov5;
    private boolean ov51;
    private boolean ov52;

    public StatisticsFilter() {
        this.dateDebut = LocalDate.now();
        this.dateFin = LocalDate.now();
        this.ll = true;
    }
}
