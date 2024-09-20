package com.recovery.fun.dto.request;

import com.recovery.fun.entity.Patient;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class PatientRequest {

    private String name;

    private String lastName;

    private String dni;

    private String email;

    private String phone;

    private LocalDate dateOfBirth;


    public static Patient toEntity(PatientRequest patientRequest){
        if(patientRequest == null){
            return null;
        }
        return Patient.builder()
                .name(patientRequest.getName())
                .lastName(patientRequest.getLastName())
                .dni(patientRequest.getDni())
                .email(patientRequest.getEmail())
                .phone(patientRequest.getPhone())
                .dateOfBirth(patientRequest.getDateOfBirth())
                .build();
    }


    public static PatientRequest fromEntity(Patient patient){
        if(patient == null){
            return null;
        }
        return PatientRequest.builder()
                .name(patient.getName())
                .lastName(patient.getLastName())
                .dni(patient.getDni())
                .email(patient.getEmail())
                .phone(patient.getPhone())
                .dateOfBirth(patient.getDateOfBirth())
                .build();
    }
}
