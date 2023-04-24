package com.leoni.packaging.dao;

import com.leoni.packaging.model.Route;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RouteRepository extends JpaRepository<Route, Long> {
    @Query("from Route r where r.routeV like %:route%")
    Page<Route> findByRoute(@Param("route") String route, Pageable pageable);
}
