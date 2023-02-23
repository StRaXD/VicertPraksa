package com.example.pilotproject.service;

import com.example.pilotproject.entities.TimeTable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TimeTableService {

    void setTable(int id, TimeTable table);

    void bookDate(int tableId);

    void deleteTable(int id);

    List<TimeTable> getAvailable();
}
