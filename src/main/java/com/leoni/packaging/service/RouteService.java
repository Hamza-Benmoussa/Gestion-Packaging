package com.leoni.packaging.service;

import com.leoni.packaging.model.Route;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RouteService {
    Route findRouteById(Long id);
    List<Route> findAll();
    Page<Route> findRoute(String route, int page, int size);
    Route addRoute(Route route);
    Route updateRoute(Long id, Route route);
    void deleteRouteById(Long id);

}
