package com.example.pilotproject.repository;

import com.example.pilotproject.entities.Doctor;
import com.example.pilotproject.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface DoctorRepository extends JpaRepository<Doctor , Integer> {
    Doctor findByFirstName(String name);

   // @Query(value="SELECT d FROM Doctor d LEFT JOIN d.patients p WHERE d.id=1")
    //Page<Patient> findByPatients(Pageable page);

   // @Query(value="SELECT p FROM Patient p JOIN p.doctors d WHERE d.id=1")
    //Page<Patient> findByPatients(Pageable page);
}
