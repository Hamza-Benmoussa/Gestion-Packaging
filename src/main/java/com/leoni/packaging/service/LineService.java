package com.leoni.packaging.service;

import com.leoni.packaging.model.Line;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LineService {
    Line findLineById(Long id);
    List<Line> findAll();
    Page<Line> findLine(String line, int page, int size);
    Line addLine(Line line);
    Line updateLine(Long id, Line line);
    void deleteLineById(Long id);
}
