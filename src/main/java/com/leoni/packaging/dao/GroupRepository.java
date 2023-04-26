package com.leoni.packaging.dao;

import com.leoni.packaging.model.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query("from Group g where g.name like %:name%")
    Page<Group> findByGroup(@Param("name") String name, Pageable pageable);

}
