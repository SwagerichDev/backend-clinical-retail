package com.recovery.fun.dto.request;

import com.recovery.fun.entity.Procedure;
import com.recovery.fun.entity.Specialty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProcedureRequest {

    private String name;

    private String description;

    private BigDecimal price;

    private Long idSpecialty;


    public static ProcedureRequest fromEntity(Procedure procedure){
        if(procedure == null){
            return null;
        }
        return ProcedureRequest.builder()
                .name(procedure.getName())
                .description(procedure.getDescription())
                .price(procedure.getPrice())
                .build();
    }

    public static Procedure toEntity(ProcedureRequest procedureRequest){
        if(procedureRequest == null){
            return null;
        }
        return Procedure.builder()
                .name(procedureRequest.getName())
                .description(procedureRequest.getDescription())
                .specialty(Specialty.builder().idSpecialty(procedureRequest.getIdSpecialty()).build())
                .price(procedureRequest.getPrice())
                .build();
    }
}