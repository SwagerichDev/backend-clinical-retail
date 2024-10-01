package com.recovery.fun.service;

import com.recovery.fun.dto.response.SpecialtyPageResponse;
import com.recovery.fun.entity.Specialty;

import java.util.List;

public interface SpecialtyService {

    List<Specialty> findAll();

    Specialty findById(Long id);

    Specialty save(Specialty specialty);

    Specialty update(Specialty specialty, Long id);

    SpecialtyPageResponse findAllPage(int page, int size);
}
