package com.leoni.packaging.service.implementation;

import com.leoni.packaging.dao.CableRepository;
import com.leoni.packaging.dao.PackageRepository;
import com.leoni.packaging.dto.LineCablesCountDto;
import com.leoni.packaging.dto.PackageLinesCablesCountDto;
import com.leoni.packaging.dto.StatisticsFilter;
import com.leoni.packaging.model.Package;
import com.leoni.packaging.service.LineService;
import com.leoni.packaging.service.StatisticService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class StatisticServiceImpl implements StatisticService {
    private final PackageRepository packageRepository;
    private final CableRepository cableRepository;
    private final LineService lineService;

    @Override
    public List<PackageLinesCablesCountDto> findPackages(StatisticsFilter filter) {
        List<PackageLinesCablesCountDto> packagesLines = new ArrayList<>();
        List<Package> packages = packageRepository.findAll();
        packages.forEach(aPackage -> {
            List<LineCablesCountDto> lines = cableRepository.countByLine(aPackage.getId());
            packagesLines.add(new PackageLinesCablesCountDto(aPackage, lines));
        });
        return packagesLines;
    }
}
