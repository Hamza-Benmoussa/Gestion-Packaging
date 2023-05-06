package com.leoni.packaging.dao;

import com.leoni.packaging.model.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    @Query("from AppUser user where user.login like :username")
    Optional<AppUser> findByUsername(@Param("username")String username);

    @Query("from AppUser user where user.name like %:search% or user.login like %:search%")
    Page<AppUser> findAppUser(@Param("search") String search, Pageable pageable);

}
