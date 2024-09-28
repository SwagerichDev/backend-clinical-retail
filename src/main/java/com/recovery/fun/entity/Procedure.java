package com.recovery.fun.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "procedures")
@SequenceGenerator(name = "seq_procedure", sequenceName = "seq_procedures", allocationSize = 1)
public class Procedure implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_procedure")
    @Column(name = "id_procedure")
    private Long idProcedure;

    private String name;

    private String description;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "id_specialty")
    private Specialty specialty;

    @JsonIgnore
    @OneToMany(mappedBy = "procedure")
    private List<Quote> quotes = new ArrayList<>();


    @Override
    public String toString() {
        return "Procedure{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
