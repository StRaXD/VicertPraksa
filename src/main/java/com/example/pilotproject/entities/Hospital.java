package com.example.pilotproject.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "hospital")
@Getter @Setter @NoArgsConstructor @ToString
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hospital")
    private int id;

    @Column(name="hospital_name")
    private String hospitalName;

    @Column(name="city")
    private String city;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "address")
    private String address;

    @OneToMany(fetch = FetchType.LAZY , mappedBy = "hospital")
    private List<Doctor> doctors;

    @OneToMany(fetch = FetchType.LAZY , mappedBy = "hospital")
    private List<Review> reviews;

    public Hospital(String hospitalName, String city, String zipCode, String address) {
        this.hospitalName = hospitalName;
        this.city = city;
        this.zipCode = zipCode;
        this.address = address;
    }


}
