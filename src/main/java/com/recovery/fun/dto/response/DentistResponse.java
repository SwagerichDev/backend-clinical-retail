package com.recovery.fun.dto.response;

import java.time.LocalDate;

public record DentistResponse(

        Long idDentist,
        String name,
        String lastName,
        LocalDate dateOfBirth
) {
}
