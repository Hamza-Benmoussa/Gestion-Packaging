package com.leoni.packaging.dao;

import com.leoni.packaging.model.Line;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineRepository extends JpaRepository<Line, Long> {
}
