package com.recovery.fun.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "quotes")
@SequenceGenerator(name = "seq_quote", sequenceName = "seq_quotes", allocationSize = 1)
public class Quote implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_quote")
    @Column(name = "id_quote")
    private Long idQuote;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime datee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_patient")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_procedure")
    private Procedure procedure;

//    @Enumerated(EnumType.STRING)
    private boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_clinical_history")
    private ClinicalHistory clinicalHistory;

    @Override
    public String toString() {
        return "Quote{" +
                "idQuote=" + idQuote +
                ", datee=" + datee +
                ", patient=" + patient +
                ", procedure=" + procedure +
                '}';
    }
}
