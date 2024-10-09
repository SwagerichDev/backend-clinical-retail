package com.recovery.fun.service.impl;

import com.recovery.fun.dto.request.DentistRequest;
import com.recovery.fun.dto.response.DentistPageResponse;
import com.recovery.fun.dto.response.DentistResponse;
import com.recovery.fun.entity.Dentist;
import com.recovery.fun.entity.Specialty;
import com.recovery.fun.proyection.DentistProyection;
import com.recovery.fun.repository.DentistRepository;
import com.recovery.fun.repository.SpecialtyRepository;
import com.recovery.fun.service.DentistService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DentistServiceImpl implements DentistService {

    private final DentistRepository dentistRepository;

    private final SpecialtyRepository specialtyRepository;


    /**
     * Method to save a dentist
     * LO CREARA EL ADMINISTRADOR
     *
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
                .status(true)
                .specialty(specialty)
                .build();
        Dentist saveEntity = dentistRepository.save(dentist);
        return new DentistResponse(saveEntity.getIdDentist(), saveEntity.getName(), saveEntity.getLastName(), saveEntity.getDateOfBirth());
    }

    @Override
    @Transactional(readOnly = true)
    public List<DentistProyection> fetchAllDentistAndSpecialty(Long idSpecialty) {
        return dentistRepository.fetchAllDentistAndSpecialty(idSpecialty);
    }

    @Override
    @Transactional(readOnly = true)
    public DentistPageResponse findAllPage(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Dentist> pages = dentistRepository.findAll(pageRequest);
        List<DentistResponse> dentists = pages.getContent().stream().map(p -> new DentistResponse(p.getIdDentist(),
                p.getName(),
                p.getLastName(),
                p.getDateOfBirth(),
                p.getEmail(),
                p.getPhone(),
                p.isStatus(),
                p.getDni(),
                p.getSpecialty().getIdSpecialty(),
                p.getSpecialty().getName())).toList();
        Map<String, Object> response = Map.of(
                "totalPages", pages.getTotalPages(),
                "totalElements", pages.getTotalElements(),
                "currentPage", pages.getNumber()
        );
        return new DentistPageResponse(dentists, response);
    }

    @Override
    @Transactional
    public DentistResponse update(DentistRequest dentistRequest, Long idDentist) {
        Specialty specialty = specialtyRepository.findById(dentistRequest.getIdSpecialty()).orElseThrow(() -> new RuntimeException("Specialty not found"));
        return dentistRepository.findById(idDentist).map(dentist -> {

            dentist.setName(dentistRequest.getName());
            dentist.setLastName(dentistRequest.getLastName());
            dentist.setDni(dentistRequest.getDni());
            dentist.setPhone(dentistRequest.getPhone());
            dentist.setEmail(dentistRequest.getEmail());
            dentist.setDateOfBirth(dentistRequest.getDateOfBirth());
            dentist.setSpecialty(specialty);
            Dentist saveDentist = dentistRepository.save(dentist);
            return new DentistResponse(saveDentist.getIdDentist(), saveDentist.getName(), saveDentist.getLastName(), saveDentist.getDateOfBirth());
        }).orElseThrow(() -> new RuntimeException("Specialty not found"));

    }

    @Override
    @Transactional(readOnly = true)
    public DentistResponse findById(Long idDentist) {
        return dentistRepository.findById(idDentist)
                .map(dentist -> new DentistResponse(dentist.getIdDentist(),
                        dentist.getName(),
                        dentist.getLastName(),
                        dentist.getDateOfBirth(),
                        dentist.getEmail(),
                        dentist.getPhone(),
                        dentist.isStatus(),
                        dentist.getDni(),
                        dentist.getSpecialty().getIdSpecialty(),
                        dentist.getSpecialty().getName()))
                .orElseThrow(() -> new RuntimeException("Dentist not found"));
    }

    @Override
    public void enabledOrDisabledDentist(Long idDentist, boolean status) {
        Dentist dentist = dentistRepository.findById(idDentist).orElseThrow(() -> new RuntimeException("Dentist not found"));
        dentist.setStatus(status);
        dentistRepository.save(dentist);
    }
}
