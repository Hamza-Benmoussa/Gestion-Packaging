package com.leoni.packaging.dao;

import com.leoni.packaging.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
