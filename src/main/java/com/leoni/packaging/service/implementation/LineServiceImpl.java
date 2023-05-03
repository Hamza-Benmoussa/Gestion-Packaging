package com.leoni.packaging.service.implementation;

import com.leoni.packaging.dao.LineRepository;
import com.leoni.packaging.model.Line;
import com.leoni.packaging.service.LineService;
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
public class LineServiceImpl implements LineService {
    private final LineRepository lineRepository;
    private final RouteService routeService;

    @Override
    public Line findLineById(Long id) {
        return lineRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Line Not Found"));
    }

    @Override
    public List<Line> findAll() {
        return lineRepository.findAll();
    }

    @Override
    public Page<Line> findLine(String line, int page, int size) {
        return lineRepository.findByLine(line,PageRequest.of(page, size));
    }

    @Override
    public Line addLine(Line line) {
        line.setId(null);
        return lineRepository.save(line);
    }

    @Override
    public Line updateLine(Long id, Line line) {
        findLineById(id);
        line.setId(id);
        return lineRepository.save(line);
    }

    @Override
    public void deleteLineById(Long id) {
        lineRepository.deleteById(id);
    }
}
