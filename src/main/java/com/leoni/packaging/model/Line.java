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
@Table(name="Lines")
@EntityListeners(AuditingEntityListener.class)
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Line {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LINE_SEQ")
    @SequenceGenerator(name = "LINE_SEQ", sequenceName = "LINE_SEQUENCE", allocationSize = 1)
    @Column(name = "LINE_ID", insertable = false, updatable = false)
    private Long id;
    @Column(name="LINE", length = 50, unique = true, nullable = false)
    private String lineV;
    @Column(name = "CAPACITY")
    private int capacity;


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
