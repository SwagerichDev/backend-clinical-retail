package com.recovery.fun.service.impl;

import com.recovery.fun.dto.request.PatientRequest;
import com.recovery.fun.dto.response.PatientPageResponse;
import com.recovery.fun.dto.response.PatientResponse;
import com.recovery.fun.entity.Patient;
import com.recovery.fun.repository.PatientRepository;
import com.recovery.fun.service.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Override
    @Transactional
    public PatientRequest createPatient(PatientRequest patientRequest) {
        Patient patient = PatientRequest.toEntity(patientRequest);
        return PatientRequest.fromEntity(patientRepository.save(patient));
    }

    @Override
    @Transactional
    public PatientRequest updatePatient(PatientRequest patientRequest, Long idPatient) {
        return patientRepository.findById(idPatient)
                .map(pa -> {
                    pa.setName(patientRequest.getName());
                    pa.setLastName(patientRequest.getLastName());
                    pa.setDni(patientRequest.getDni());
                    pa.setEmail(patientRequest.getEmail());
                    pa.setPhone(patientRequest.getPhone());
                    pa.setDateOfBirth(patientRequest.getDateOfBirth());
                    return patientRepository.save(pa);
                })
                .map(PatientRequest::fromEntity)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    @Override
    public PatientPageResponse getPatients(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size).withSort(Sort.Direction.DESC, "idPatient");
        Page<Patient> patients = patientRepository.findAll(pageRequest);
        List<PatientResponse> patientsResponse = patients.getContent().stream().map(p -> new PatientResponse(p.getIdPatient(), p.getName(), p.getLastName(), p.getDni(), p.getEmail(), p.getPhone(), p.getDateOfBirth())).toList();
        Map<String, Object> pagination = Map.of(
                "totalElements", patients.getTotalElements(),
                "totalPages", patients.getTotalPages(),
                "currentPage", patients.getNumber()
        );
        return new PatientPageResponse(patientsResponse, pagination);
    }

    @Override
    @Transactional(readOnly = true)
    public PatientResponse getPatientById(Long idPatient) {
        return patientRepository.findById(idPatient)
                .map(p -> new PatientResponse(p.getIdPatient(), p.getName(), p.getLastName(), p.getDni(), p.getEmail(), p.getPhone(), p.getDateOfBirth()))
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }
}
