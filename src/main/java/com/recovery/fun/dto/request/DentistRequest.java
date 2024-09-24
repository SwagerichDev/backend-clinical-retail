package com.recovery.fun.dto.request;


import lombok.Data;

import java.time.LocalDate;

@Data
public class DentistRequest {

    private String name;

    private String lastName;

    private String dni;

    private String email;

    private String phone;

    private LocalDate dateOfBirth;

    private Long idSpecialty;
}
