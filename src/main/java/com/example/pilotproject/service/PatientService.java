package com.example.pilotproject.service;

import com.example.pilotproject.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface PatientService {
    void savePatient(int id, Patient patient);

    void deletePatient(int id);

    Patient getPatient(int id);

    Patient getByName(String name);

    void assignPatient(int patientId, int doctorId);

    void removePatient(int patientId, int doctorId);

    Page<Patient> getPatients(Integer page);

}
