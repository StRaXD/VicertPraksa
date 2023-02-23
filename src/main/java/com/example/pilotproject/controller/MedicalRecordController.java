package com.example.pilotproject.controller;

import com.example.pilotproject.entities.MedicalRecord;
import com.example.pilotproject.entities.Patient;
import com.example.pilotproject.service.MedicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    @PostMapping("/record/{id}")
    public void saveRecord(@PathVariable("id") int id,@RequestBody MedicalRecord record){
        medicalRecordService.saveRecord(id,record);
    }

    @PutMapping("/record/{id}")
    public void updateRecord(@PathVariable("id") int id,@RequestBody MedicalRecord record){
        medicalRecordService.updateRecord(id, record);
    }
}