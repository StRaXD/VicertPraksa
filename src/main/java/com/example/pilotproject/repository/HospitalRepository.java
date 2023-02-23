package com.example.pilotproject.repository;

import com.example.pilotproject.entities.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital,Integer> {
    Hospital findByHospitalName(String name);
}
