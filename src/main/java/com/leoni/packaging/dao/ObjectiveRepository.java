package com.leoni.packaging.dao;

import com.leoni.packaging.model.Objective;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ObjectiveRepository extends JpaRepository<Objective, Long> {
    @Query("from Objective ob order by ob.startDate desc")
    Page<Objective> findByObjective(Pageable pageable);
}
