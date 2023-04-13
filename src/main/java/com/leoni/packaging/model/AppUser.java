package com.leoni.packaging.model;

import com.leoni.packaging.enums.Role;
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
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class AppUser {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
    @SequenceGenerator(name = "USER_SEQ", sequenceName = "USER_SEQUENCE", allocationSize = 1)
    @Column(name = "user_id", insertable = false, updatable = false)
    private Long id;
    @Column(name = "user_name", length = 100)
    private String name;
    @Column(name = "user_login", length = 100, unique = true , nullable = false)
    private String login;
    @Column(name = "user_password", nullable = false)
    private String password;
    @Column(name = "user_role", length = 100, nullable = false)
    private Role role;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="group_id", referencedColumnName="group_id")
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
