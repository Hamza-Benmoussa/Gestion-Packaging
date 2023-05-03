package com.leoni.packaging.dao;

import com.leoni.packaging.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    @Query("from Supplier s where s.supplierCode like :code")
    Optional<Supplier> findByCode(@Param("code") String code);
}
