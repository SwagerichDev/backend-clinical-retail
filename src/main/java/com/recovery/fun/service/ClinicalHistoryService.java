package com.recovery.fun.service;

import com.recovery.fun.dto.request.ClinicalHistoryRequest;
import com.recovery.fun.entity.ClinicalHistory;

import java.util.List;

public interface ClinicalHistoryService {

    List<ClinicalHistory> fetchClinicalHistoryByPatientId(Long idPatient);


    void updateClinicalHistory(ClinicalHistoryRequest clinicalHistory, Long idPatient);
}
