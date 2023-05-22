package com.leoni.packaging.dto;

import com.leoni.packaging.enums.ScanKey;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScanDto {
    private ScanKey Key;
    private String Value;
    private LocalDateTime scanDateTime;

    public ScanDto(ScanKey key) {
        Key = key;
    }
}
