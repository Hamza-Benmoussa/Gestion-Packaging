package com.leoni.packaging.service.implementation;

import com.leoni.packaging.dao.RouteRepository;
import com.leoni.packaging.model.Route;
import com.leoni.packaging.service.RouteService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class RouteServiceImpl implements RouteService {
    private final RouteRepository routeRepository;

    @Override
    public Route findRouteById(Long id) {
        return routeRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Route Not Found"));
    }

    @Override
    public List<Route> findAll() {
        return routeRepository.findAll();
    }

    @Override
    public Page<Route> findRoute(String route, int page, int size) {
        return routeRepository.findByRoute(route, PageRequest.of(page, size));
    }

    @Override
    public Route addRoute(Route route) {
        return routeRepository.save(route);
    }

    @Override
    public Route updateRoute(Long id, Route route) {
        findRouteById(id);
        route.setId(id);
        return routeRepository.save(route);
    }

    @Override
    public void deleteRouteById(Long id) {
        routeRepository.deleteById(id);
    }
}
