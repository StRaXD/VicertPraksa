package com.example.pilotproject.service;

import com.example.pilotproject.entities.Doctor;
import com.example.pilotproject.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DoctorService {

    void save(int id, Doctor doctor);

    Doctor getDoctor(int id);

    List<Doctor> getAllDoctors();

    void deleteDoctor(int id);

    Doctor getByName(String name);

    void updateDoctor(int id,Doctor doctor);

}
