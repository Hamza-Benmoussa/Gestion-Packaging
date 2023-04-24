package com.leoni.packaging.service.implementation;

import com.leoni.packaging.dao.ObjectiveRepository;
import com.leoni.packaging.model.Objective;
import com.leoni.packaging.service.ObjectiveService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ObjectiveServiceImpl implements ObjectiveService {
    private final ObjectiveRepository objectiveRepository;
    @Override
    public Page<Objective> findObjective(String objective, int page, int size) {
        return null;
    }

    @Override
    public Objective addObjective(Objective objective) {
        return null;
    }

    @Override
    public Objective updateObjective(Long id, Objective objective) {
        return null;
    }

    @Override
    public void deleteObjectiveById(Long id) {

    }
}
