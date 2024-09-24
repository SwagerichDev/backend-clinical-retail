package com.recovery.fun.dto.request;


import lombok.Data;

import java.time.LocalDateTime;


@Data
public class QuoteRequest {

    private Long idPatient;

    private Long idProcedure;

    private Long idDentist;

    private String clinicalHistoryDescription;
//    private List<Long> idProcedure; luego veo si se puede agregar varios procedimiento al cliente

    private LocalDateTime date;
}
