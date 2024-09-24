package com.recovery.fun.controller;

import com.recovery.fun.dto.request.DentistRequest;
import com.recovery.fun.service.DentistService;
import com.recovery.fun.util.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/dentist")
public class DentistRestController {

    private final DentistService dentistService;

    public DentistRestController(DentistService dentistService) {
        this.dentistService = dentistService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createDentist(@RequestBody DentistRequest dentistRequest) {
        return new ResponseEntity<>(ApiResponse.create("Dentist Create Succefull",dentistService.save(dentistRequest)), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getDentists() {
        return new ResponseEntity<>(ApiResponse.ok("get Dentists And Specialty succefull", dentistService.fetchAllDentistAndSpecialty()), HttpStatus.OK);
    }
}
