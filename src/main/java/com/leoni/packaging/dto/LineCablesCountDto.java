package com.leoni.packaging.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class LineCablesCountDto {
    private String lineName;
    private long cables;
}
