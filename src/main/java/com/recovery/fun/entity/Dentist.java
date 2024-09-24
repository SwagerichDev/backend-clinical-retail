package com.recovery.fun.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "dentists")
@SequenceGenerator(name = "seq_dentist", sequenceName = "seq_dentists", allocationSize = 1)
public class Dentist {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_dentist")
    @Column(name = "id_dentist")
    private Long idDentist;

    private String name;

    @Column(name = "last_name")
    private String lastName;

    private String dni;

    private String email;

    private String phone;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    private boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_specialty")
    private Specialty specialty;

}
