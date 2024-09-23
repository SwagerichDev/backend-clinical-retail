package com.recovery.fun.service.impl;

import com.recovery.fun.dto.request.ClinicalHistoryRequest;
import com.recovery.fun.dto.response.ClinicalHistoryResponse;
import com.recovery.fun.entity.ClinicalHistory;
import com.recovery.fun.entity.Patient;
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
    public void updateClinicalHistory(ClinicalHistoryRequest clinicalHistory, Long idClinicalHistory) {
        ClinicalHistory clinicalHistoryEntity = clinicalHistoryRepository.findById(idClinicalHistory)
                .map(ch -> {
                    ch.setDescription(clinicalHistory.getDescription());
                    ch.setDatee(LocalDate.now());
                    return ch;
                }).orElseThrow(() -> new RuntimeException("Clinical history not found"));
        clinicalHistoryRepository.save(clinicalHistoryEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public ClinicalHistoryResponse findById(Long id) {
       return clinicalHistoryRepository.findById(id)
                .map(ch -> new ClinicalHistoryResponse(ch.getDescription(), ch.getDatee()))
                .orElseThrow(() -> new RuntimeException("Clinical history not found"));
    }
}
