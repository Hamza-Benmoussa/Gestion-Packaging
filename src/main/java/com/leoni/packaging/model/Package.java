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
@Table(name = "PACKAGES")
@EntityListeners(AuditingEntityListener.class)
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Package {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PACKAGE_SEQ")
    @SequenceGenerator(name = "PACKAGE_SEQ", sequenceName = "PACKAGE_SEQUENCE", allocationSize = 1)
    @Column(name = "PACKAGE_ID", insertable = false, updatable = false)
    private Long id;
    @Column(name = "ETICKET", length = 100, nullable = false)
    private String barCode;
    @Column(name = "QUANTITE_TOTAL")
    private int totalQuantity;
    @Column(name = "QUANTITE_COURANT")
    private int currentQuatity;
    @Column(name = "DATE_DEBUT")
    private LocalDateTime dateDebut;
    @Column(name = "DATE_FIN")
    private LocalDateTime dateFin;
    @Column(name = "STATUS")
    private String state;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FOURNISSEUR_ID", referencedColumnName = "FOURNISSEUR_ID")
    private Supplier supplier;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROUTE_ID", referencedColumnName = "ROUTE_ID")
    private Route route;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LINE_ID", referencedColumnName = "LINE_ID")
    private Line line;


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
