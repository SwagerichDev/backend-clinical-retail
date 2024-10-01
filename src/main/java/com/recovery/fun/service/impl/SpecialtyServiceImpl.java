package com.recovery.fun.service.impl;

import com.recovery.fun.dto.response.SpecialtyPageResponse;
import com.recovery.fun.dto.response.SpecialtyResponse;
import com.recovery.fun.entity.Specialty;
import com.recovery.fun.repository.SpecialtyRepository;
import com.recovery.fun.service.SpecialtyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
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

    /**
     * ESTO VA  LISTAR EL ADMINISTRADOR
     *
     * @param size
     * @param page
     * @return SpecialtyPageResponse
     */
    @Override
    @Transactional(readOnly = true)
    public SpecialtyPageResponse findAllPage(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Specialty> pages = specialtyRepository.findAll(pageRequest);
        List<SpecialtyResponse> specialtyResponses = pages.getContent().stream().map(specialty -> new SpecialtyResponse(specialty.getIdSpecialty(), specialty.getName())).toList();
        Map<String, Object> convert = Map.of(
                "totalPages", pages.getTotalPages(),
                "totalElements", pages.getTotalElements(),
                "currentPage", pages.getNumber()
        );
        return new SpecialtyPageResponse(specialtyResponses, convert);
    }


    /**
     * ESTO VA A ACTUALIZAR ADMINISTRADOR
     * @return Specialty
     * @param id
     */
    @Override
    @Transactional
    public Specialty update(Specialty specialty, Long id) {
        return specialtyRepository.findById(id).map(s -> {
            s.setName(specialty.getName());
            return specialtyRepository.save(s);
        }).orElseThrow(() -> new RuntimeException("Specialty not found"));
    }
}
