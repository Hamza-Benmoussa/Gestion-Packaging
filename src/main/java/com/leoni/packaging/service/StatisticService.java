package com.leoni.packaging.service;

import com.leoni.packaging.dto.*;
import com.leoni.packaging.model.Cable;

import java.time.LocalDate;
import java.util.List;

public interface StatisticService {

    List<RouteCablesCountDto> findCablesByRoute(StatisticsFilter filter);
    List<CablesByHourDto> getCountCablesForEachHour();

    List<CableResponseDto> getCablesByRoute(Long routeId, LocalDate dateDebut, LocalDate dateFin);
}
