package com.example.pilotproject.service;


import com.example.pilotproject.entities.Hospital;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HospitalService {

    void save(Hospital hospital);

    List<Hospital> getAll();

    Hospital getById(int id);

    void deleteHospital(int id);

    void updateHospital(int id, Hospital hospital);

    Hospital getByName(String name);


}
