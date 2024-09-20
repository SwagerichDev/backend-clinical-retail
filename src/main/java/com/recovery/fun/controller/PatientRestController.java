package com.recovery.fun.controller;

import com.recovery.fun.dto.request.PatientRequest;
import com.recovery.fun.service.PatientService;
import com.recovery.fun.util.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/patient")
public class PatientRestController {

    private final PatientService patientService;

    public PatientRestController(PatientService patientService) {
        this.patientService = patientService;
    }


    @PostMapping
    public ResponseEntity<ApiResponse> createPatient(@RequestBody PatientRequest patientRequest) {
        return new ResponseEntity<>(ApiResponse.create("Patient Create Succefull",patientService.createPatient(patientRequest)), HttpStatus.CREATED);
    }


    @PutMapping("/{idPatient}")
    public ResponseEntity<ApiResponse> updatePatient(@RequestBody PatientRequest patientRequest, @PathVariable Long idPatient) {
        return new ResponseEntity<>(ApiResponse.create("Patient Update",patientService.updatePatient(patientRequest, idPatient)), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getPatients(@RequestParam int page, @RequestParam int size) {
        return new ResponseEntity<>(ApiResponse.ok("get Patients With Page", patientService.getPatients(page, size)), HttpStatus.OK);
    }

    @GetMapping("/{idPatient}")
    public ResponseEntity<ApiResponse> getPatient(@PathVariable Long idPatient) {
        return new ResponseEntity<>(ApiResponse.ok("get Patient", patientService.getPatientById(idPatient)), HttpStatus.OK);
    }
}
