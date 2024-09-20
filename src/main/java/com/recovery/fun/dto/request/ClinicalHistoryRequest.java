package com.recovery.fun.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ClinicalHistoryRequest {

    private String description;

    private LocalDate datee;

}
