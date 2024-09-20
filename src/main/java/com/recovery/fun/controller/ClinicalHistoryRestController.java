package com.recovery.fun.controller;

import com.recovery.fun.dto.request.ClinicalHistoryRequest;
import com.recovery.fun.entity.ClinicalHistory;
import com.recovery.fun.service.ClinicalHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/clinical-history")
public class ClinicalHistoryRestController {

    private final ClinicalHistoryService clinicalHistoryService;

    public ClinicalHistoryRestController(ClinicalHistoryService clinicalHistoryService) {
        this.clinicalHistoryService = clinicalHistoryService;
    }


    @GetMapping("/patient/{idPatient}")
    public ResponseEntity<List<ClinicalHistory>> fetchClinicalHistoryByPatientId(@PathVariable  Long idPatient) {
        return new ResponseEntity<>(clinicalHistoryService.fetchClinicalHistoryByPatientId(idPatient), HttpStatus.OK);
    }


    @PutMapping
    public ResponseEntity<Void> updateClinicalHistory(@RequestBody ClinicalHistoryRequest clinicalHistory, @RequestParam Long idPatient) {
        clinicalHistoryService.updateClinicalHistory(clinicalHistory, idPatient);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
