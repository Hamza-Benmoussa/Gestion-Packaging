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
@Table(name = "objectives")
@EntityListeners(AuditingEntityListener.class)
@Data @AllArgsConstructor @Builder
public class Objective {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "objective_id", insertable = false, updatable = false)
    private Long id;
    @Column(name = "current_objective")
    private int currentObjective;
    @Column(name = "quantite_produite")
    private int currentQuantity;
    @Column(name = "date_debut")
    private LocalDateTime startDate;
    @Column(name = "date_limit")
    private LocalDateTime endDate;
//    @ManyToOne
//    @JoinColumn(name = "line_id", referencedColumnName = "line_id")
//    private Line line;
    @ManyToOne
    @JoinColumn(name= "group_id", referencedColumnName = "group_id")
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

    public Objective() {
        this.startDate = LocalDateTime.now();
        this.endDate = LocalDateTime.now().plusDays(1);
    }
}
