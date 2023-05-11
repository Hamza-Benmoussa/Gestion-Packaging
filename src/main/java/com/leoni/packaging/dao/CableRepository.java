package com.leoni.packaging.dao;

import com.leoni.packaging.dto.CablesByHourDto;
import com.leoni.packaging.dto.LineCablesCountDto;
import com.leoni.packaging.model.Cable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CableRepository extends JpaRepository<Cable, Long> {

    @Query("select new com.leoni.packaging.dto.LineCablesCountDto(l.lineName,count(*)) " +
            "from Cable c inner join c.line l inner join l.route r " +
            "where r.id = :routeId " +
            "group by l.lineName")
    List<LineCablesCountDto> countCablesByRoute(@Param("routeId") Long routeId);

    @Query("select c " +
            "from Cable c inner join c.line l inner join l.route r " +
            "where r.id = :routeId")
    Page<Cable> findCablesByRoute(@Param("routeId") Long routeId, Pageable pageable);

    @Query("SELECT new com.leoni.packaging.dto.CablesByHourDto(HOUR(c.createdDate), COUNT(*))" +
            "FROM Cable c " +
            "WHERE DATE(c.createdDate) = CURRENT_DATE " +
            "AND (HOUR(c.createdDate) > :startHour " +
            "OR (HOUR(c.createdDate) = :startHour AND MINUTE(c.createdDate) >= :startMinute)) " +
            "AND (HOUR(c.createdDate) < :endHour " +
            "OR (HOUR(c.createdDate) = :endHour AND MINUTE(c.createdDate) <= :endMinute)) " +
            "GROUP BY HOUR(c.createdDate) " +
            "ORDER BY HOUR(c.createdDate)")
    List<CablesByHourDto> findCablesByHour(@Param("startHour") int startHour,
                                          @Param("startMinute") int StartMinute,
                                          @Param("endHour") int endHour,
                                          @Param("endMinute") int endMinute);

    @Query("from Cable c where c.aPackage.id = :packageId order by c.createdDate desc limit 3")
    List<Cable> findLastCablesByPackage(Long packageId);
}
