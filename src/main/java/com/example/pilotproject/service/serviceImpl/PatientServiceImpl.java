package com.example.pilotproject.service.serviceImpl;

import com.example.pilotproject.entities.Doctor;
import com.example.pilotproject.entities.Patient;
import com.example.pilotproject.repository.DoctorRepository;
import com.example.pilotproject.repository.PatientRepository;
import com.example.pilotproject.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @Override
    public void savePatient(int id, Patient patient) {
        boolean exists = doctorRepository.existsById(id);
        if (!exists) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Can't find doctor with id: " + id);
        }else{
            Doctor doctor = doctorRepository.findById(id).get();
            doctor.getPatients().add(patient);
            patientRepository.save(patient);
        }
    }

    @Override
    public Patient getPatient(int id) {
        boolean exists = patientRepository.existsById(id);
        if (!exists) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Failed to get patient with id: " + id);
        }else
           return patientRepository.findById(id).get();
    }

    @Override
    public Patient getByName(String name) {
       return patientRepository.findByFirstName(name);
    }

    @Override
    public void deletePatient(int id) {
        patientRepository.deleteById(id);
    }

    @Override
    @Modifying
    public void assignPatient(int patientId, int doctorId) {
        boolean existsPatient = patientRepository.existsById(patientId);
        boolean existsDoctor = doctorRepository.existsById(doctorId);
        if (!existsPatient || !existsDoctor) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "You selected non-existing entities");
        }else{
            Doctor doctor = doctorRepository.findById(doctorId).get();
            Patient patient= patientRepository.findById(patientId).get();
            doctor.getPatients().add(patient);
            doctorRepository.save(doctor);
        }
    }

    @Override
    @Modifying
    public void removePatient(int patientId , int doctorId) {
        boolean patientExist = patientRepository.existsById(patientId);
        boolean doctorExist = doctorRepository.existsById(doctorId);
        if (!patientExist || !doctorExist) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Can't find selected entities");
        }else{
            Doctor doctor = doctorRepository.findById(doctorId).get();
            Patient patient = patientRepository.findById(patientId).get();
            if(doctor.getPatients().contains(patient)){
                doctor.getPatients().remove(patient);
                doctorRepository.save(doctor);
            }else
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Selected patient is not under this doctor");
        }
    }

    @Override
    public Page<Patient> getPatients(Integer page) {
        Pageable pageable = PageRequest.of(page, 1);
        return patientRepository.findAll(pageable);
    }


}
