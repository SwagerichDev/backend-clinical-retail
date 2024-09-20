package com.recovery.fun.service;

import com.recovery.fun.dto.request.PatientRequest;
import com.recovery.fun.dto.response.PatientPageResponse;
import com.recovery.fun.dto.response.PatientResponse;

public interface PatientService {

    PatientRequest createPatient(PatientRequest patientRequest);

    PatientRequest updatePatient(PatientRequest patientRequest, Long idPatient);

    PatientPageResponse getPatients(int page, int size);

    PatientResponse getPatientById(Long idPatient);
}
