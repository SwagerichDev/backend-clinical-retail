package com.recovery.fun.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DentistResponse {

    private Long idDentist;

    private String name;

    private String lastName;

    private LocalDate dateOfBirth;

    private String email;

    private String phone;

    private boolean status;

    private String specialty;


    public DentistResponse(Long idDentist, String name, String lastName, LocalDate dateOfBirth, String email, String phone, boolean status, String specialty) {
        this.idDentist = idDentist;
        this.name = name;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.specialty = specialty;
    }

    public DentistResponse(Long idDentist, String name, String lastName, LocalDate dateOfBirth) {
        this.idDentist = idDentist;
        this.name = name;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }
}
