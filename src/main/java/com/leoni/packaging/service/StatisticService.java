package com.leoni.packaging.service;

import com.leoni.packaging.dto.PackageLinesCablesCountDto;
import com.leoni.packaging.dto.StatisticsFilter;

import java.util.List;

public interface StatisticService {

    List<PackageLinesCablesCountDto> findPackages(StatisticsFilter filter);

}
