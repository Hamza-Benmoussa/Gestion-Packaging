package com.leoni.packaging.dao;

import com.leoni.packaging.model.Line;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LineRepository extends JpaRepository<Line, Long> {
    @Query("from Line l where l.lineV like %:line%")
    Page<Line> findByLine(@Param("line") String line, Pageable pageable);
}
