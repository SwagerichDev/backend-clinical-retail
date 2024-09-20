package com.recovery.fun.service;

import com.recovery.fun.dto.request.ProcedureRequest;
import com.recovery.fun.dto.response.ProcedurePageResponse;
import com.recovery.fun.dto.response.ProcedureResponse;
import com.recovery.fun.entity.Procedure;

import java.util.List;

public interface ProcedureService {

    Procedure createProcedure(ProcedureRequest procedure);

    Procedure updateProcedure(ProcedureRequest procedure, Long procedureId);

    ProcedurePageResponse getAllProcedures(int page, int size);

    Procedure getProcedureById(Long procedureId);

    List<ProcedureResponse> getAllProcedures();
}
