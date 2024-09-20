package com.recovery.fun.service.impl;

import com.recovery.fun.dto.request.QuoteRequest;
import com.recovery.fun.dto.response.QuotePageResponse;
import com.recovery.fun.dto.response.QuoteResponse;
import com.recovery.fun.entity.ClinicalHistory;
import com.recovery.fun.entity.Patient;
import com.recovery.fun.entity.Procedure;
import com.recovery.fun.entity.Quote;

import com.recovery.fun.proyection.QuoteProyectionDto;
import com.recovery.fun.repository.ClinicalHistoryRepository;
import com.recovery.fun.repository.PatientRepository;
import com.recovery.fun.repository.ProcedureRepository;
import com.recovery.fun.repository.QuoteRepository;
import com.recovery.fun.service.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService {

    private final QuoteRepository quoteRepository;

    private final PatientRepository patientRepository;

    private final ProcedureRepository procedureRepository;

    private final ClinicalHistoryRepository clinicalHistoryRepository;

    /**
     * Crear una cita
     *
     * @param quote
     * @return QuoteResponse
     */

    @Override
    public QuoteResponse createQoute(QuoteRequest quote) {
        Patient patient = patientRepository.findById(quote.getIdPatient()).orElseThrow(() -> new RuntimeException("Patient not found"));
        Procedure procedure = procedureRepository.findById(quote.getIdProcedure()).orElseThrow(() -> new RuntimeException("Procedure not found"));

        ClinicalHistory clinicalHistory = ClinicalHistory.builder()
                .description(quote.getClinicalHistoryDescription())
                .datee(LocalDate.now())
                .patient(patient)
                .build();
        // AQUI SUPONGAMOS QUE EL CLIENTE YA HA PAGADO Y SE QUIERE agendar la cita
        Quote quoteToSave = Quote.builder()
                .datee(quote.getDate())
                .patient(patient)
                .procedure(procedure)
                .clinicalHistory(clinicalHistory)
                .status(true)
                .build();

        clinicalHistoryRepository.save(clinicalHistory);
        quoteRepository.save(quoteToSave);
        return new QuoteResponse(quoteToSave.getIdQuote(), quoteToSave.getDatee(),quoteToSave.isStatus());
    }


    /**
     * Reprogranar la cita
     *
     * @param quote
     * @param quoteId
     * @return QuoteResponse
     */
    @Override
    public QuoteResponse updateQoute(QuoteRequest quote, Long quoteId) {
        return quoteRepository.findById(quoteId)
                .map(quoteEntity -> {
                    quoteEntity.setDatee(quote.getDate());
                    return quoteRepository.save(quoteEntity);
                }).map(quoteEntity -> new QuoteResponse(quoteEntity.getIdQuote(),quoteEntity.getDatee(),quoteEntity.isStatus()))
                .orElseThrow(() -> new RuntimeException("Quote not found"));

    }

    /**
     * Cancelar una cita
     *
     * @param quoteId
     */
    @Override
    public void cancelQuote(Long quoteId) {
        quoteRepository.findById(quoteId).map(quote -> {
            quote.setStatus(false);
            return quoteRepository.save(quote);
        }).orElseThrow(() -> new RuntimeException("Quote not found"));
    }

    /**
     * Obtener una cita por id
     *
     * @param quoteId
     * @return Quote
     */
    @Override
    public QuoteResponse getQuoteById(Long quoteId) {
        return quoteRepository.findById(quoteId)
                .map(quote -> new QuoteResponse(quote.getIdQuote(),quote.getDatee(),quote.isStatus()))
                .orElseThrow(() -> new RuntimeException("Quote not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public QuotePageResponse getAllQuoteByPatientId(int page, int size, Long idPatient) {
        Patient patient = patientRepository.findById(idPatient).orElseThrow(() -> new RuntimeException("Patient not found"));
        Pageable pageable = PageRequest.of(page, size);
        Page<QuoteProyectionDto> pages = quoteRepository.getQuotesByPatientId(pageable, patient.getIdPatient());
        List<QuoteProyectionDto> quoteProyectionDtos = pages.getContent().stream().toList();
        Map<String, Object> response = Map.of(
                "totalPages", pages.getTotalPages(),
                "totalElements", pages.getTotalElements(),
                "currentPage", pages.getNumber()
        );
        return new QuotePageResponse(quoteProyectionDtos, response);
    }
}
