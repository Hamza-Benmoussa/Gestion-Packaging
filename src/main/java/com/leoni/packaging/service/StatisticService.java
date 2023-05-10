package com.leoni.packaging.service;

import com.leoni.packaging.dto.CablesByHourDto;
import com.leoni.packaging.dto.RouteCablesCountDto;
import com.leoni.packaging.dto.StatisticsFilter;

import java.util.List;

public interface StatisticService {

    List<RouteCablesCountDto> findCablesByRoute(StatisticsFilter filter);
    List<CablesByHourDto> getCountCablesForEachHour();

}
