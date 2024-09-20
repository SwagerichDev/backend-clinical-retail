package com.recovery.fun.service;

import com.recovery.fun.dto.request.QuoteRequest;
import com.recovery.fun.dto.response.QuotePageResponse;
import com.recovery.fun.dto.response.QuoteResponse;


public interface QuoteService {


    QuoteResponse createQoute(QuoteRequest quote);


    QuoteResponse updateQoute(QuoteRequest quote, Long quoteId);


    QuoteResponse getQuoteById(Long quoteId);


    void cancelQuote(Long quoteId);

    QuotePageResponse getAllQuoteByPatientId(int page, int size, Long idPatient);
}
