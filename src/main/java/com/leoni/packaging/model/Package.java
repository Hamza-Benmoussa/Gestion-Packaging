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
import java.util.List;

@Entity
@Table(name = "packages")
@EntityListeners(AuditingEntityListener.class)
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Package {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "package_id", insertable = false, updatable = false)
    private Long id;
    @Column(name = "package_name", length = 100)
    private String packageName;
    @Column(name = "eticket", length = 100)
    private String barCode;
    @Column(name = "quantite_total")
    private int totalQuantity;
    @Column(name = "quantite_courant")
    private int currentQuatity;
    @Column(name = "date_debut")
    private LocalDateTime dateDebut;
    @Column(name = "date_fin")
    private LocalDateTime dateFin;
    @Column(name = "status")
    private String state;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fournisseur_id", referencedColumnName = "fournisseur_id")
    private Supplier supplier;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "aPackage")
    private List<Cable> cables;


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

    @Override
    public String toString() {
        return "Package{" +
                "id=" + id +
                ", packageName='" + packageName + '\'' +
                ", barCode='" + barCode + '\'' +
                ", totalQuantity=" + totalQuantity +
                ", currentQuatity=" + currentQuatity +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", state='" + state + '\'' +
                ", supplier=" + supplier +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                ", createdBy='" + createdBy + '\'' +
                ", modifiedBy='" + modifiedBy + '\'' +
                '}';
    }
}
