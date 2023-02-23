package com.example.pilotproject.repository;

import com.example.pilotproject.entities.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeTableRepository extends JpaRepository<TimeTable,Integer> {

    @Query("SELECT t FROM TimeTable t WHERE t.available=true")
    List<TimeTable> getAvailable();
}
