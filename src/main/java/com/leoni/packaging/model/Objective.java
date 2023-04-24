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
@Table(name = "OBJECTIVES")
@EntityListeners(AuditingEntityListener.class)
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Objective {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OBJECTIVE_SEQ")
    @SequenceGenerator(name = "OBJECTIVE_SEQ", sequenceName = "OBJECTIVE_SEQUENCE", allocationSize = 1)
    @Column(name = "OBJECTIVE_ID", insertable = false, updatable = false)
    private Long id;
    @Column(name = "CURRENT_OBJECTIVE")
    private int currentObjective;
    @Column(name = "QUANTITE_PRODUITE")
    private int currentQuantity;
    @Column(name = "DATE_DEBUT")
    private LocalDateTime startDate;
    @Column(name = "DATE_LIMIT")
    private LocalDateTime endDate;
    @ManyToOne
    @JoinColumn(name = "LINE_ID", referencedColumnName = "LINE_ID")
    private Line line;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "GROUP_ID", referencedColumnName = "GROUP_ID")
    private Group group;


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
