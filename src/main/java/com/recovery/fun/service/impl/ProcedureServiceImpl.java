package com.recovery.fun.service.impl;

import com.recovery.fun.dto.request.ProcedureRequest;
import com.recovery.fun.dto.response.ProcedurePageResponse;
import com.recovery.fun.dto.response.ProcedureResponse;
import com.recovery.fun.entity.Procedure;
import com.recovery.fun.entity.Specialty;
import com.recovery.fun.repository.ProcedureRepository;
import com.recovery.fun.repository.SpecialtyRepository;
import com.recovery.fun.service.ProcedureService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProcedureServiceImpl implements ProcedureService {

    private final ProcedureRepository procedureRepository;

    private final SpecialtyRepository specialtyRepository;

    @Override
    @Transactional
    public Procedure createProcedure(ProcedureRequest procedure) {
        Procedure proced = ProcedureRequest.toEntity(procedure);
        return procedureRepository.save(proced);
    }

    @Override
    @Transactional
    public Procedure updateProcedure(ProcedureRequest procedure, Long procedureId) {
        Specialty specialty = specialtyRepository.findById(procedure.getIdSpecialty()).orElseThrow(() -> new RuntimeException("Specialty not found"));
        return procedureRepository.findById(procedureId).map(proce -> {
            proce.setName(procedure.getName());
            proce.setDescription(procedure.getDescription());
            proce.setPrice(procedure.getPrice());
            proce.setSpecialty(specialty);
            return procedureRepository.save(proce);
        }).orElseThrow(() -> new RuntimeException("Procedure not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public ProcedurePageResponse getAllProcedures(int page, int size) {
        Pageable pageable = PageRequest.of(page, size).withSort(Sort.Direction.DESC, "idProcedure");
        Page<Procedure> pages = procedureRepository.findAll(pageable);
        List<ProcedureResponse> content = pages.getContent().stream().map(p -> new ProcedureResponse(p.getIdProcedure(), p.getName(), p.getDescription(), p.getPrice(), p.getSpecialty().getName(),p.getSpecialty().getIdSpecialty())).toList();
        Map<String, Object> response = Map.of(
                "totalPages", pages.getTotalPages(),
                "totalElements", pages.getTotalElements(),
                "currentPage", pages.getNumber()
        );
        return new ProcedurePageResponse(content, response);
    }

    @Override
    @Transactional(readOnly = true)
    public Procedure getProcedureById(Long procedureId) {
        return procedureRepository.findById(procedureId).orElseThrow(() -> new RuntimeException("Procedure not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProcedureResponse> getAllProcedures() {
        return procedureRepository.findAll().stream().map(p -> new ProcedureResponse(p.getIdProcedure(), p.getName(), p.getDescription(), p.getPrice(),p.getSpecialty().getName(),p.getSpecialty().getIdSpecialty())).toList();
    }
}
