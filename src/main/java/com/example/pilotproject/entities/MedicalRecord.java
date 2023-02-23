package com.example.pilotproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "medical_record")
@Getter @Setter @NoArgsConstructor
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medical")
    private int id;

    @Column(name = "social_security")
    private String socialSecurity;

    @Column(name = "age")
    private int age;

    @Column(name = "vaccinated")
    private Boolean vaccinated;

    @Column(name = "city")
    private String city;

    @Column(name = "address")
    private String address;

    @OneToOne(fetch = FetchType.EAGER , mappedBy = "medicalRecord",cascade = CascadeType.ALL)
    @JsonIgnore
    private Patient patient;

    public MedicalRecord(String socialSecurity, int age, Boolean vaccinated, String city, String address) {
        this.socialSecurity = socialSecurity;
        this.age = age;
        this.vaccinated = vaccinated;
        this.city = city;
        this.address = address;
    }

}
