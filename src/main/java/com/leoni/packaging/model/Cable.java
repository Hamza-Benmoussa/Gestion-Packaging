package com.leoni.packaging.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "CABLES")
@EntityListeners(AuditingEntityListener.class)
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Cable {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CABLE_SEQ")
    @SequenceGenerator(name = "CABLE_SEQ", sequenceName = "CABLE_SEQUENCE", allocationSize = 1)
    @Column(name = "CABLE_ID", insertable = false, updatable = false)
    private Long id;
    @Column(name="CABLE_CODE_BAR", length = 100, nullable = false, unique = true)
    private String barCode;
    @Column(name="CABLE_TYPE")
    private String type;
    @Column(name="STEERING")
    private String steering;
    @Column(name="RESULT")
    private String result;
    @Column(name="SCAN_STARTED")
    private LocalDateTime started;
    @Column(name="SCAN_COMPLETED")
    private LocalDateTime completed;
    @Column(name="SCAN_DURATION")
    private int duration;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PACKAGE_ID", referencedColumnName = "PACKAGE_ID")
    private Package aPackage;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LINE_ID", referencedColumnName = "LINE_ID")
    private Line line;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROUTE_ID", referencedColumnName = "ROUTE_ID")
    private Route route;

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;
    @Column(name = "modified_date", nullable = false, updatable = false)
    @LastModifiedDate
    private LocalDateTime modifiedDate;
    @Column(name = "created_by", nullable = false, updatable = false)
    @CreatedBy
    private String createdBy;
    @Column(name = "modified_by", nullable = false, updatable = false)
    @LastModifiedBy
    private String modifiedBy;

}
