package com.leoni.packaging.service;

import com.leoni.packaging.dto.*;
import com.leoni.packaging.model.Cable;

import java.util.List;

public interface StatisticService {

    List<RouteCablesCountDto> findCablesByRoute(StatisticsFilter filter);
    List<CablesByHourDto> getCountCablesForEachHour();

    PageResponse<CableResponseDto> getCablesByRoute(Long routeId, int page, int size);
}
