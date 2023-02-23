package com.example.pilotproject.repository;

import com.example.pilotproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String mail);

    @Modifying
    @Transactional
    @Query("UPDATE User a " + "SET a.active = TRUE WHERE a.email = ?1")
    int enableUser(String email);
}
