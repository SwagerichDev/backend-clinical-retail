package com.recovery.fun.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "specialties")
@SequenceGenerator(name = "seq_specialty", sequenceName = "seq_specialties", allocationSize = 1)
public class Specialty {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_specialty")
    @Column(name = "id_specialty")
    private Long idSpecialty;

    private String name;

}
