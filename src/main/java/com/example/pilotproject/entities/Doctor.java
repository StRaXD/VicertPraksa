package com.example.pilotproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "doctor")
@Getter @Setter @NoArgsConstructor @ToString
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_doctor")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "specialization")
    private String specialization;

    @Column(name = "age")
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Hospital hospital;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(
            name = "patient_doctor",
            joinColumns = @JoinColumn(name = "Doctor_id_doctor"),
            inverseJoinColumns = @JoinColumn(name = "Patient_id_patient"))
    private List<Patient> patients;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "doctor",cascade = CascadeType.ALL)
    private List<TimeTable> times;


    public Doctor(String firstName, String lastName, String specialization, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;
        this.age = age;
    }

}
