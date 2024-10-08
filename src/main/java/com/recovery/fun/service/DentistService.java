package com.recovery.fun.service;

import com.recovery.fun.dto.request.DentistRequest;
import com.recovery.fun.dto.response.DentistPageResponse;
import com.recovery.fun.dto.response.DentistResponse;
import com.recovery.fun.proyection.DentistProyection;

import java.util.List;

public interface DentistService {


    DentistResponse save(DentistRequest dentistRequest);

    List<DentistProyection> fetchAllDentistAndSpecialty(Long idSpecialty);

    DentistPageResponse findAllPage(int page, int size);

    DentistResponse update(DentistRequest dentistRequest, Long idDentist);

    DentistResponse findById(Long idDentist);

    void enabledOrDisabledDentist(Long idDentist, boolean status);
}
