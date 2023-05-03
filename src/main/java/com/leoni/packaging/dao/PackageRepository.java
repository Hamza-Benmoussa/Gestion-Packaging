package com.leoni.packaging.dao;

import com.leoni.packaging.model.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface PackageRepository extends JpaRepository<Package, Long>{

    @Query("from Package p where p.barCode like :code")
    Optional<Package> findByCode(@Param("code") String code);


}
