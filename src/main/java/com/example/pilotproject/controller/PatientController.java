package com.example.pilotproject.controller;

import com.example.pilotproject.entities.Patient;
import com.example.pilotproject.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PatientController {

    private final PatientService patientService;

    @PostMapping("/patient/{id}")
    public void savePatient(@PathVariable("id") int id,@RequestBody Patient patient){
        patientService.savePatient(id, patient);
    }

    @DeleteMapping("/patient/{id}")
    public void deletePatient(@PathVariable("id") int id){
        patientService.deletePatient(id);
    }

    @GetMapping("/patient/{id}")
    public Patient getPatient(@PathVariable("id") int id){
        return patientService.getPatient(id);
    }

    @GetMapping("/patient/search/{name}")
    public Patient getPatientName(@PathVariable("name") String name){
        return patientService.getByName(name);
    }

    @PutMapping("/patient/{patientid}/{doctorid}")
    public void assignPatient(@PathVariable("patientid") int patientId,@PathVariable("doctorid") int doctorId){
        patientService.assignPatient(patientId, doctorId);
    }

    @DeleteMapping("/patient/{patientid}/{doctorid}")
    public void removePatient(@PathVariable("patientid") int patientId,@PathVariable("doctorid") int doctorId){
        patientService.removePatient(patientId, doctorId);
    }

    @GetMapping("/patient/all")
    public Page<Patient> grabAll(@RequestParam(defaultValue = "0",name = "page", required = false) int pageNumber){
        return patientService.getPatients(pageNumber);
    }

}
