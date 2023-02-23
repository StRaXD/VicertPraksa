package com.example.pilotproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "review")
@Getter @Setter @NoArgsConstructor
public class Review {

    @Id
    @Column(name = "id_review")
    private int id;

    @Column(name = "comment")
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    Hospital hospital;

    public Review(String comment) {
        this.comment = comment;
    }

}
