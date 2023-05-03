package com.leoni.packaging.dao;

import com.leoni.packaging.dto.LineCablesCountDto;
import com.leoni.packaging.model.Cable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CableRepository extends JpaRepository<Cable, Long> {

    @Query("select new com.leoni.packaging.dto.LineCablesCountDto(l.lineName, count(*)) "+
            "from Cable c join Line l on l.id = c.line.id " +
            "where c.aPackage.id = :packageId " +
            "group by l.lineName")
    List<LineCablesCountDto> countByLine(@Param("packageId") Long packageId);

}
