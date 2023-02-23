package com.example.pilotproject.service.serviceImpl;

import com.example.pilotproject.entities.Doctor;
import com.example.pilotproject.entities.TimeTable;
import com.example.pilotproject.repository.DoctorRepository;
import com.example.pilotproject.repository.TimeTableRepository;
import com.example.pilotproject.service.TimeTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeTableServiceImpl implements TimeTableService {

    private final TimeTableRepository timeTableRepository;
    private final DoctorRepository doctorRepository;

    @Override
    public void setTable(int id, TimeTable table) {
        boolean exists = doctorRepository.existsById(id);
        if (!exists) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Can't find doctor with id: " + id);
        }else{
            Doctor doctor = doctorRepository.findById(id).get();
            table.setDoctor(doctor);
            timeTableRepository.save(table);
        }
    }

    @Override
    @Modifying
    public void bookDate(int tableId) {
        boolean tableExists = timeTableRepository.existsById(tableId);
        if(!tableExists){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Failed to get table with id: " + tableId);
        }else{
            TimeTable table = timeTableRepository.findById(tableId).get();
            table.setAvailable(false);
            timeTableRepository.save(table);
        }
    }

    @Override
    public void deleteTable(int id) {
        timeTableRepository.deleteById(id);
    }

    @Override
    public List<TimeTable> getAvailable() {
        return timeTableRepository.getAvailable();
    }
}
