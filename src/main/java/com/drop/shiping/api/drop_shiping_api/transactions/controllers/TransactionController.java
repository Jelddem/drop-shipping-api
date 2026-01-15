package com.drop.shiping.api.drop_shiping_api.transactions.controllers;

import com.drop.shiping.api.drop_shiping_api.transactions.dtos.TransactionResponseDTO;
import com.drop.shiping.api.drop_shiping_api.transactions.dtos.UserInfoDTO;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import com.drop.shiping.api.drop_shiping_api.common.exceptions.NotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import com.drop.shiping.api.drop_shiping_api.transactions.entities.Transaction;
import com.drop.shiping.api.drop_shiping_api.transactions.dtos.NewTransactionDTO;
import com.drop.shiping.api.drop_shiping_api.transactions.services.TransactionService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/app/transactions")
@CrossOrigin(originPatterns = "*")
public class TransactionController {
    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping
    public Page<TransactionResponseDTO> viewAllByUser(
    @RequestHeader(value = "token", required = false) String token,
    @CookieValue(name = "userReference", required = false) String userRef, @PageableDefault Pageable pageable) {
        return service.findAllByUser(token, userRef, pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<TransactionResponseDTO> view(@PathVariable String id) {
        Optional<TransactionResponseDTO> orderDb = service.findOne(id);

        return orderDb.map(order -> ResponseEntity.ok().body(order))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> createTransaction(@Valid @RequestBody NewTransactionDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createTransaction(dto));
    }

    @PutMapping("/update-products/{id}")
    public ResponseEntity<String> updateProducts(
    @PathVariable("id") String id, @RequestBody List<String> productIds) {
        return ResponseEntity.ok().body(service.updateProducts(id, productIds));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> addUserInfo(
    @CookieValue(value = "userReference", required = false) String userReference,
    @PathVariable("id") String id, @RequestHeader(value = "token", required = false) String token,
    @Valid @RequestBody UserInfoDTO dto, HttpServletResponse response) {
        return ResponseEntity.ok().body(service.addUserInfo(userReference, response, id, dto, token));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        Optional<Transaction> transactionDb = service.delete(id);
        transactionDb.orElseThrow(() -> new NotFoundException("Transaction not found"));

        return ResponseEntity.ok().build();
    }
}
