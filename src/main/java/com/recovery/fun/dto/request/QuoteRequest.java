package com.recovery.fun.dto.request;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class QuoteRequest {

    private Long idPatient;

    private Long idProcedure;

    private String clinicalHistoryDescription;
//    private List<Long> idProcedure; luego veo si se puede agregar varios procedimiento al cliente

    private LocalDateTime date;
}
