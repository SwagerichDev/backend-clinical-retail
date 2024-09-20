package com.recovery.fun.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "clinical_histories")
@SequenceGenerator(name = "seq_clinical_history", sequenceName = "seq_clinical_histories", allocationSize = 1)
public class ClinicalHistory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_clinical_history")
    @Column(name = "id_clinical_history")
    private Long idClinicalHistory;

    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate datee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_patient")
    private Patient patient;


    @Override
    public String toString() {
        return "ClinicalHistory{" +
                "idClinicalHistory=" + idClinicalHistory +
                ", patient=" + patient +
                ", description='" + description + '\'' +
                ", datee=" + datee +
                '}';
    }
}
