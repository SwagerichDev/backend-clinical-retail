package com.recovery.fun.service.impl;

import com.recovery.fun.entity.Specialty;
import com.recovery.fun.repository.SpecialtyRepository;
import com.recovery.fun.service.SpecialtyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service @RequiredArgsConstructor
public class SpecialtyServiceImpl implements SpecialtyService {

    private final SpecialtyRepository specialtyRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Specialty> findAll() {
        return specialtyRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Specialty findById(Long id) {
        return specialtyRepository.findById(id).orElseThrow(() -> new RuntimeException("Specialty not found"));
    }

    /**
     * ESTO VA CREAR EL ADMINISTRADOR
     */
    @Override
    @Transactional
    public Specialty save(Specialty specialty) {
        return specialtyRepository.save(specialty);
    }
}
