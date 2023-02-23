package com.example.pilotproject.service.serviceImpl;

import com.example.pilotproject.entities.Hospital;
import com.example.pilotproject.repository.HospitalRepository;
import com.example.pilotproject.service.HospitalService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HospitalServiceImpl implements HospitalService {

    private final HospitalRepository hospitalRepository;

    @Override
    public void save(Hospital hospital) {
        hospitalRepository.save(hospital);
    }

    @Override
    public List<Hospital> getAll() {
        return hospitalRepository.findAll();
    }

    @Override
    public Hospital getById(int id) {
        boolean exists = hospitalRepository.existsById(id);
        if(!exists){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hospital with id: " + id + " not found");
        }else
        return hospitalRepository.findById(id).get();
    }

    @Override
    public Hospital getByName(String name) {
        return hospitalRepository.findByHospitalName(name);
    }

    @Override
    public void deleteHospital(int id) {
        boolean exists = hospitalRepository.existsById(id);
        if(!exists){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Failed to delete hospital with id: " + id);
        }else
        hospitalRepository.deleteById(id);
    }

    @Override
    @Modifying
    public void updateHospital(int id, Hospital hospital) {
        boolean exists = hospitalRepository.existsById(id);
        if (!exists) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Failed to update hospital with " + id);
        }else {
            Hospital dbHospital = hospitalRepository.findById(id).get();
            dbHospital.setHospitalName(hospital.getHospitalName());
            dbHospital.setCity(hospital.getCity());
            dbHospital.setZipCode(hospital.getZipCode());
            dbHospital.setAddress(hospital.getAddress());
            hospitalRepository.save(dbHospital);
        }
    }
}
