package com.recovery.fun.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "patients")
@SequenceGenerator(name = "seq_patient", sequenceName = "seq_patients", allocationSize = 1)
public class Patient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_patient")
    @Column(name = "id_patient")
    private Long idPatient;

    private String name;

    @Column(name = "last_name")
    private String lastName;

    private String dni;

    private String email;

    private String phone;

    @Column(name = "date_of_birth")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate  dateOfBirth;


    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Quote> quotes = new ArrayList<>();

    @Override
    public String toString() {
        return "Patient{" +
                "phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", dni='" + dni + '\'' +
                ", lastName='" + lastName + '\'' +
                ", name='" + name + '\'' +
                ", idPatient=" + idPatient +
                '}';
    }
}
