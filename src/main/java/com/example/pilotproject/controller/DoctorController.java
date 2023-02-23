package com.example.pilotproject.controller;

import com.example.pilotproject.entities.Doctor;
import com.example.pilotproject.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping("/doctor/{id}")
    public void saveDoctor(@PathVariable("id") int id,@RequestBody Doctor doctor){
      doctorService.save(id, doctor);
    }

    @GetMapping("/doctor/{id}")
    public Doctor getDoctor(@PathVariable("id") int id){
        return doctorService.getDoctor(id);
    }

    @GetMapping("/doctor/search/{name}")
    public Doctor getByName(@PathVariable("name") String name){
        return doctorService.getByName(name);
    }

    @GetMapping("/doctor")
    public List<Doctor> getAllDoctors(){
        return doctorService.getAllDoctors();
    }

    @DeleteMapping("/doctor/{id}")
    public void deleteDoctor(@PathVariable("id") int id){
        doctorService.deleteDoctor(id);
    }

    @PutMapping("/doctor/{id}")
    public void updateDoctor(@PathVariable("id") int id,@RequestBody Doctor doctor){
        doctorService.updateDoctor(id, doctor);
    }

}
