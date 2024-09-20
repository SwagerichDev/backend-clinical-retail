package com.recovery.fun.controller;

import com.recovery.fun.dto.request.ProcedureRequest;
import com.recovery.fun.service.ProcedureService;
import com.recovery.fun.util.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/procedure")
public class ProcedureRestController {

    private final ProcedureService procedureService;

    public ProcedureRestController(ProcedureService procedureService) {
        this.procedureService = procedureService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createProcedure(@RequestBody ProcedureRequest procedure) {
        return new ResponseEntity<>(ApiResponse.create("Procedure successfully created", procedureService.createProcedure(procedure)), HttpStatus.CREATED);
    }

    @PutMapping("/{procedureId}")
    public ResponseEntity<ApiResponse> updateProcedure(@RequestBody ProcedureRequest procedure, @PathVariable Long procedureId) {
        return new ResponseEntity<>(ApiResponse.create("Procedure successfully  updated", procedureService.updateProcedure(procedure, procedureId)), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllProcedures(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return new ResponseEntity<>(ApiResponse.ok("All procedures successfully fetched", procedureService.getAllProcedures(page,size)), HttpStatus.OK);
    }

    @GetMapping("/{procedureId}")
    public ResponseEntity<ApiResponse> getProcedureById(@PathVariable Long procedureId) {
        return new ResponseEntity<>(ApiResponse.ok("Procedure successfully fetched", procedureService.getProcedureById(procedureId)), HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllProcedures() {
        return new ResponseEntity<>(ApiResponse.ok("All procedures successfully fetched", procedureService.getAllProcedures()), HttpStatus.OK);
    }
}
