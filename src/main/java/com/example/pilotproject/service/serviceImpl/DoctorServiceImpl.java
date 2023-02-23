package com.example.pilotproject.service.serviceImpl;

import com.example.pilotproject.entities.Doctor;
import com.example.pilotproject.entities.Hospital;
import com.example.pilotproject.repository.DoctorRepository;
import com.example.pilotproject.repository.HospitalRepository;
import com.example.pilotproject.service.DoctorService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;

    @Override
    public void save(int id, Doctor doctor) {
        boolean exists = hospitalRepository.existsById(id);
        if(!exists){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Failed to get hospital with id " + id);
        }else {
            Hospital hospital = hospitalRepository.findById(id).get();
            doctor.setHospital(hospital);
            doctorRepository.save(doctor);

        }
    }

    @Override
    public Doctor getDoctor(int id) {
        boolean exists = doctorRepository.existsById(id);
        if(!exists){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor with id " + " not found");
        }else
            return doctorRepository.findById(id).get();
    }

    @Override
    public Doctor getByName(String name) {
        return doctorRepository.findByFirstName(name);
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public void deleteDoctor(int id) {
        boolean exists = doctorRepository.existsById(id);
        if (!exists) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Failed to delete doctor with id " + id);
        }else if(exists){
            Doctor doctor = doctorRepository.findById(id).get();
            if(!doctor.getPatients().isEmpty()){
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can't delete doctor that has patients");
            }else
                doctorRepository.deleteById(id);
        }
    }

    @Override
    @Modifying
    public void updateDoctor(int id,Doctor doctor) {
        boolean exists = doctorRepository.existsById(id);
        if(!exists){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Failed to update doctor with id: " + id);
        }else {
            Doctor dbDoc = doctorRepository.findById(id).get();
            dbDoc.setFirstName(doctor.getFirstName());
            dbDoc.setLastName(doctor.getLastName());
            dbDoc.setSpecialization(doctor.getSpecialization());
            dbDoc.setAge(doctor.getAge());
            doctorRepository.save(dbDoc);
        }
    }

}
