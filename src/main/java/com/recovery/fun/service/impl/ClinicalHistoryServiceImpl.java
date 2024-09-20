package com.recovery.fun.service.impl;

import com.recovery.fun.dto.request.ClinicalHistoryRequest;
import com.recovery.fun.entity.ClinicalHistory;
import com.recovery.fun.entity.Patient;
import com.recovery.fun.entity.Procedure;
import com.recovery.fun.repository.ClinicalHistoryRepository;
import com.recovery.fun.repository.PatientRepository;
import com.recovery.fun.service.ClinicalHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service @RequiredArgsConstructor @Slf4j
public class ClinicalHistoryServiceImpl implements ClinicalHistoryService {

    private final ClinicalHistoryRepository clinicalHistoryRepository;

    private final PatientRepository patientRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ClinicalHistory> fetchClinicalHistoryByPatientId(Long idPatient) {
        return clinicalHistoryRepository.getClinicalHistoryByPatientIdPacientOOrderByFecha(idPatient);
    }

    @Override
    @Transactional
    public void updateClinicalHistory(ClinicalHistoryRequest clinicalHistory, Long idPatient) {
        Patient patient = patientRepository.findById(idPatient).orElseThrow(() -> new RuntimeException("Patient not found"));

        ClinicalHistory clinicalHistoryEntity = ClinicalHistory.builder()
                .description(clinicalHistory.getDescription())
                .datee(LocalDate.now())
                .patient(patient)
                .build();
        clinicalHistoryRepository.save(clinicalHistoryEntity);
    }
}
