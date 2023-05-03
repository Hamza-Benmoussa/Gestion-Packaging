package com.leoni.packaging.service;

import com.leoni.packaging.model.Objective;
import org.springframework.data.domain.Page;

public interface ObjectiveService {
    Objective findObjectiveById(Long id);
    Page<Objective> findObjective(int page, int size);
    Objective addObjective(Objective objective);
    Objective updateObjective(Long id, Objective objective);
    void deleteObjectiveById(Long id);
}
