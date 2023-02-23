package com.example.pilotproject.controller;

import com.example.pilotproject.entities.Hospital;
import com.example.pilotproject.service.HospitalService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HospitalController {
    private final HospitalService hospitalService;

    @PostMapping("/hospital")
    public void save(@RequestBody Hospital hospital){
        hospitalService.save(hospital);
    }

    @GetMapping("/hospital")
    public List<Hospital> getAll(){
        return hospitalService.getAll();
    }

    @GetMapping("/hospital/{id}")
    public Hospital getById(@PathVariable("id") int id){
        return hospitalService.getById(id);
    }

    @GetMapping("/hospital/search/{name}")
    public Hospital getByName(@PathVariable("name") String name){
        return hospitalService.getByName(name);
    }

    @DeleteMapping("/hospital/{id}")
    public void deleteHospital(@PathVariable("id") int id){
        hospitalService.deleteHospital(id);
    }

    @PutMapping("/hospital/{id}")
    public void updateHospital(@PathVariable("id")int id, @RequestBody Hospital hospital){
        hospitalService.updateHospital(id, hospital);
    }


}
