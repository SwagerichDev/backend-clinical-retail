package com.recovery.fun.repository;

import com.recovery.fun.entity.Dentist;
import com.recovery.fun.proyection.DentistProyection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DentistRepository extends JpaRepository<Dentist, Long> {

    @Query("SELECT d.idDentist as idDentist, d.name as name, d.lastName as lastName, s.name as specialty,d.status as status FROM Dentist d JOIN  d.specialty s  WHERE d.specialty.idSpecialty =:idSpecialty")
    List<DentistProyection> fetchAllDentistAndSpecialty(Long idSpecialty);
}
