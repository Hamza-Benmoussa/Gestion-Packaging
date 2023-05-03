package com.leoni.packaging.dto;

import com.leoni.packaging.enums.ScanKey;
import lombok.Data;

@Data
public class ScanDto {
    private ScanKey Key;
    private String Value;

    public ScanDto(ScanKey key) {
        Key = key;
    }
}
