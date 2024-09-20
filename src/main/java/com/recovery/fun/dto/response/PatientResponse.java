package com.recovery.fun.dto.response;

import java.time.LocalDate;

public record PatientResponse(
        Long idPatient,
        String name,
        String lastName,
        String dni,
        String email,
        String phone,
        LocalDate dateOfBirth
) {
}
