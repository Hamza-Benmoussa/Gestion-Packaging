package com.leoni.packaging.service.implementation;

import com.leoni.packaging.dao.CableRepository;
import com.leoni.packaging.dto.*;
import com.leoni.packaging.model.AppUser;
import com.leoni.packaging.model.Cable;
import com.leoni.packaging.model.Group;
import com.leoni.packaging.model.Route;
import com.leoni.packaging.service.RouteService;
import com.leoni.packaging.service.StatisticService;
import com.leoni.packaging.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class StatisticServiceImpl implements StatisticService {
    private final CableRepository cableRepository;
    private final RouteService routeService;
    private final UserService userService;

    @Override
    public List<RouteCablesCountDto> findCablesByRoute(StatisticsFilter filter) {
        List<RouteCablesCountDto> routeCablesCounts = new ArrayList<>();
        List<Route> routes = routeService.findAll();
        routes.forEach(route->{
            RouteCablesCountDto routeCablesCount = new RouteCablesCountDto(route, null, 0);
            System.out.println("Waaaaaaaaa3::"+ filter);
            List<LineCablesCountDto> lineCablesCount = cableRepository.countCablesByRoute(route.getId(), filter.getDateDebut(), filter.getDateFin());
            routeCablesCount.setLines(lineCablesCount);
            lineCablesCount.forEach(line->{
                routeCablesCount.setTotalCables(routeCablesCount.getTotalCables()+line.getCables());
            });
            routeCablesCounts.add(routeCablesCount);
        });
        return routeCablesCounts;
    }

    @Override
    public List<CablesByHourDto> getCountCablesForEachHour() {
        AppUser appUser = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            appUser =  userService.findUserByUsername(currentUserName);
        }
        Group group = appUser.getGroup();
        LocalTime startDate = group.getWorkingTime().getStartTime();
        LocalTime endDate = group.getWorkingTime().getEndTime();
        return cableRepository.findCablesByHour(startDate.getHour(), startDate.getMinute(), endDate.getHour(), endDate.getMinute());
    }

    @Override
    public List<CableResponseDto> getCablesByRoute(Long routeId, LocalDate dateDebut, LocalDate dateFin) {
        List<Cable> cables = cableRepository.findCablesByRoute(routeId, dateDebut, dateFin);
        return cables.stream()
                .map(CableResponseDto::getCableResponseDtoFromCable)
                .collect(Collectors.toList());
    }
}
