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

    @GetMapping("/{idSpecialty}")
    public ResponseEntity<ApiResponse> getDentistsAndSpecialty(@PathVariable Long idSpecialty) {
        return new ResponseEntity<>(ApiResponse.ok("get Dentists And Specialty succefull", dentistService.fetchAllDentistAndSpecialty(idSpecialty)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getDentistsPage(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        return new ResponseEntity<>(ApiResponse.ok("get Dentists succefull", dentistService.findAllPage(page, size)), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateDentist(@RequestBody DentistRequest dentistRequest, @PathVariable Long id) {
        return new ResponseEntity<>(ApiResponse.create("Dentist Update Succefull", dentistService.update(dentistRequest, id)), HttpStatus.CREATED);
    }
}
