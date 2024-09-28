package com.recovery.fun.service.impl;

import com.recovery.fun.dto.request.DentistRequest;
import com.recovery.fun.dto.response.DentistResponse;
import com.recovery.fun.entity.Dentist;
import com.recovery.fun.entity.Specialty;
import com.recovery.fun.proyection.DentistProyection;
import com.recovery.fun.repository.DentistRepository;
import com.recovery.fun.repository.SpecialtyRepository;
import com.recovery.fun.service.DentistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @RequiredArgsConstructor
public class DentistServiceImpl implements DentistService {

    private final DentistRepository dentistRepository;

    private final SpecialtyRepository specialtyRepository;


    /**
     * Method to save a dentist
     * LO CREARA EL ADMINISTRADOR
     * @param dentistRequest
     * @return
     */
    @Override
    @Transactional
    public DentistResponse save(DentistRequest dentistRequest) {
        Specialty specialty = specialtyRepository.findById(dentistRequest.getIdSpecialty()).orElseThrow(() -> new RuntimeException("Specialty not found"));
        Dentist dentist = Dentist.builder()
                .name(dentistRequest.getName())
                .lastName(dentistRequest.getLastName())
                .dni(dentistRequest.getDni())
                .phone(dentistRequest.getPhone())
                .email(dentistRequest.getEmail())
                .dateOfBirth(dentistRequest.getDateOfBirth())
                .specialty(specialty)
                .build();
        Dentist saveEntity = dentistRepository.save(dentist);
        return new DentistResponse(saveEntity.getIdDentist(),saveEntity.getName(),saveEntity.getLastName(),saveEntity.getDateOfBirth());
    }

    @Override
    @Transactional(readOnly = true)
    public List<DentistProyection> fetchAllDentistAndSpecialty(Long idSpecialty) {
        return dentistRepository.fetchAllDentistAndSpecialty(idSpecialty);
    }

}
