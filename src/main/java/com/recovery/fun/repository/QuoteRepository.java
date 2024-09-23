package com.recovery.fun.repository;

import com.recovery.fun.entity.Quote;
import com.recovery.fun.proyection.QuoteProyection;
import com.recovery.fun.proyection.QuoteProyectionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface QuoteRepository extends CrudRepository<Quote, Long> {
    //QUERY NATIVO
//@Query("SELECT SUM(p.price) FROM Quote c INNER JOIN c.procedure p WHERE TO_CHAR(c.datee, 'YYYYMMDD') = ?1")
    @Query("SELECT SUM(p.price) FROM Quote c INNER JOIN c.procedure p WHERE FUNCTION('TO_CHAR', c.datee, 'YYYYMMDD') = ?1")
    BigDecimal calculateGrossMonthlyIncome(String date);


    @Query("SELECT p.name as name,count(c) as cantidad FROM Quote c INNER JOIN c.procedure p WHERE FUNCTION('TO_CHAR', c.datee, 'YYYYMMDD') = ?1 GROUP BY p.idProcedure,p.name")
    List<QuoteProyection> getQuotesByDate(String date);


    @Query("SELECT q.idQuote as idQuote, q.status as status,q.datee as datee, p.name as name,p.lastName as lastName,p.dni as dni,pro.idProcedure as idProcedure,pro.name as procedureName,ch.idClinicalHistory as idClinicalHistory, ch.description as description FROM Quote " +
            "q  JOIN  q.patient p  join  q.procedure pro  join q.clinicalHistory ch  where p.idPatient =?1")
    Page<QuoteProyectionDto> getQuotesByPatientId(Pageable pageable, Long idPatient);
}
