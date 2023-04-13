package com.leoni.packaging.dao;

import com.leoni.packaging.model.Cable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CableRepository extends JpaRepository<Cable, Long> {
}
