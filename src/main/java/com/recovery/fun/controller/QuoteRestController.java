package com.recovery.fun.controller;

import com.recovery.fun.dto.request.QuoteRequest;
import com.recovery.fun.service.QuoteService;
import com.recovery.fun.util.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/quote")
public class QuoteRestController {

    private final QuoteService quoteService;

    public QuoteRestController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createQuote(@RequestBody QuoteRequest quoteRequest) {
        return new ResponseEntity<>(ApiResponse.create("Quote Create Succefull", quoteService.createQoute(quoteRequest)), HttpStatus.CREATED);
    }

    @GetMapping("/quote-patientid")
    public ResponseEntity<ApiResponse> getQuoteById(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @RequestParam Long patientId) {
        return new ResponseEntity<>(ApiResponse.ok("get All quotePage by patientId Succefull  ", quoteService.getAllQuoteByPatientId(page,size,patientId)), HttpStatus.OK);
    }

    @PutMapping("/{quoteId}")
    public ResponseEntity<ApiResponse> updateQuote(@RequestBody QuoteRequest quoteRequest, @PathVariable Long quoteId) {
        return new ResponseEntity<>(ApiResponse.create("Quote Update Succefull", quoteService.updateQoute(quoteRequest, quoteId)), HttpStatus.CREATED);
    }

    @GetMapping("/{quoteId}")
    public ResponseEntity<ApiResponse> getQuoteById(@PathVariable Long quoteId) {
        return new ResponseEntity<>(ApiResponse.ok("get Quote by Id Succefull  ", quoteService.getQuoteById(quoteId)), HttpStatus.OK);
    }

    @PatchMapping("/status/{quoteId}")
    public ResponseEntity<Void> cancelQuoteStatus(@PathVariable Long quoteId) {
        quoteService.cancelQuote(quoteId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
