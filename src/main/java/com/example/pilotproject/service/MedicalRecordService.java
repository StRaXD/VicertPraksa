package com.example.pilotproject.service;

import com.example.pilotproject.entities.MedicalRecord;
import com.example.pilotproject.entities.Patient;
import org.springframework.stereotype.Service;

@Service
public interface MedicalRecordService {

    void saveRecord(int id,MedicalRecord record);

    void updateRecord(int id,MedicalRecord record);
}
