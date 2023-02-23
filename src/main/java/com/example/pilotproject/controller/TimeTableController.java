package com.example.pilotproject.controller;

import com.example.pilotproject.entities.TimeTable;
import com.example.pilotproject.service.TimeTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TimeTableController {

    private final TimeTableService timeTableService;

    @PostMapping("/table/{id}")
    public void setTable(@PathVariable("id") int id, @RequestBody TimeTable table){
        timeTableService.setTable(id, table);
    }

    @PutMapping("/table/{id}")
    public void bookTable(@PathVariable("id") int id){
        timeTableService.bookDate(id);
    }

    @DeleteMapping("/table/{id}")
    public void deleteTable(@PathVariable("id") int id){
        timeTableService.deleteTable(id);
    }

    @GetMapping("/table")
    public List<TimeTable> getAvailable(){
        return timeTableService.getAvailable();
    }
}
