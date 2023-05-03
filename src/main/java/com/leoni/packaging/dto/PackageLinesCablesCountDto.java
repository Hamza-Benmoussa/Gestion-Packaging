package com.leoni.packaging.dto;

import com.leoni.packaging.model.Package;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
public class PackageLinesCablesCountDto {
    private Package aPackage;
    private List<LineCablesCountDto> lines;

}

