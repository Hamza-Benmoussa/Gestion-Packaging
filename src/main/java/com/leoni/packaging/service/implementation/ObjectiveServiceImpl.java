package com.leoni.packaging.service.implementation;

import com.leoni.packaging.dao.ObjectiveRepository;
import com.leoni.packaging.model.Objective;
import com.leoni.packaging.service.ObjectiveService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ObjectiveServiceImpl implements ObjectiveService {
    private final ObjectiveRepository ObjectiveRepository;

    @Override
    public Objective findObjectiveById(Long id) {
        return ObjectiveRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Objective Not Found"));
    }

    @Override
    public Page<Objective> findObjective(int page, int size) {
        return ObjectiveRepository.findByObjective(PageRequest.of(page, size));
    }

    @Override
    public Objective addObjective(Objective Objective) {
        Objective.setId(null);
        return ObjectiveRepository.save(Objective);
    }

    @Override
    public Objective updateObjective(Long id, Objective Objective) {
        findObjectiveById(id);
        Objective.setId(id);
        return ObjectiveRepository.save(Objective);
    }

    @Override
    public void deleteObjectiveById(Long id) {
        ObjectiveRepository.deleteById(id);
    }
}
