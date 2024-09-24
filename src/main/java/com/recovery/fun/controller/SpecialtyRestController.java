package com.recovery.fun.controller;


import com.recovery.fun.entity.Specialty;
import com.recovery.fun.service.SpecialtyService;
import com.recovery.fun.util.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/specialty")
public class SpecialtyRestController {

    private final SpecialtyService specialtyService;

    public SpecialtyRestController(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> saveSpecialty(@RequestBody Specialty specialty) {
        return new ResponseEntity<>(ApiResponse.create("Specialty Create Succefull", specialtyService.save(specialty)), HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<ApiResponse> getSpecialties() {
        return new ResponseEntity<>(ApiResponse.ok("get Specialties succefull", specialtyService.findAll()), HttpStatus.OK);
    }
}
