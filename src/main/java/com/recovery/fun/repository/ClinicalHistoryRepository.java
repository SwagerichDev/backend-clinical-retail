package com.recovery.fun.repository;

import com.recovery.fun.entity.ClinicalHistory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClinicalHistoryRepository extends CrudRepository<ClinicalHistory, Long> {


    @Query("SELECT ch FROM ClinicalHistory ch INNER JOIN FETCH ch.patient p WHERE ch.patient.idPatient = ?1 ORDER BY ch.datee DESC")
    List<ClinicalHistory> getClinicalHistoryByPatientIdPacientOOrderByFecha(Long idPatient);
}
