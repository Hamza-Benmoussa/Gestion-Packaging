package com.leoni.packaging.dao;

import com.leoni.packaging.model.Package;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<Package, Long> {
}
