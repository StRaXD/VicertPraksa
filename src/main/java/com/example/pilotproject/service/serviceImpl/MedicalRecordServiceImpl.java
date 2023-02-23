package com.example.pilotproject.service.serviceImpl;

import com.example.pilotproject.entities.MedicalRecord;
import com.example.pilotproject.entities.Patient;
import com.example.pilotproject.repository.MedicalRecordRepository;
import com.example.pilotproject.repository.PatientRepository;
import com.example.pilotproject.service.MedicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class MedicalRecordServiceImpl implements MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;
    private final PatientRepository patientRepository;

    @Override
    public void saveRecord(int id ,MedicalRecord record) {
        Patient patient = patientRepository.findById(id).get();
        patient.setMedicalRecord(record);
        patientRepository.save(patient);
    }

    @Override
    @Modifying
    public void updateRecord(int id,MedicalRecord record) {
        boolean exists = medicalRecordRepository.existsById(id);
        if (!exists) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Failed to update record with id: " + id);
        }else{
            MedicalRecord dbRecord = medicalRecordRepository.findById(id).get();
            dbRecord.setSocialSecurity(record.getSocialSecurity());
            dbRecord.setAge(record.getAge());
            dbRecord.setVaccinated(record.getVaccinated());
            dbRecord.setCity(record.getCity());
            dbRecord.setAddress(record.getAddress());
        }
    }

}
