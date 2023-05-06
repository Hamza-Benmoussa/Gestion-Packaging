package com.leoni.packaging.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class CablesByHourDto {
    private long hour;
    private long cablesCount;
}
