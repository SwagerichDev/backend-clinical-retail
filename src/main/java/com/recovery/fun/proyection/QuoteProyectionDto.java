package com.recovery.fun.proyection;

import java.time.LocalDateTime;

public interface QuoteProyectionDto {

    Long getIdQuote();

    boolean isStatus();

    LocalDateTime getDatee();

    String getName();

    String getLastName();

    String getDni();

    Long getIdProcedure();

    String getProcedureName();


    Long  getIdClinicalHistory();

    String  getDescription();
}
