package com.itau.transaction.controller;

import com.itau.transaction.model.Statistics;
import com.itau.transaction.model.Transaction;
import com.itau.transaction.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transacao")
    @Operation(summary = "Create a new transaction", 
              description = "Creates a new transaction if it's valid (not in the future and value >= 0)")
    @ApiResponse(responseCode = "201", description = "Transaction created successfully")
    @ApiResponse(responseCode = "422", description = "Invalid transaction data")
    @ApiResponse(responseCode = "400", description = "Invalid request format")
    public ResponseEntity<Void> createTransaction(@Valid @RequestBody Transaction transaction) {
        if (transactionService.addTransaction(transaction)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
    }

    @DeleteMapping("/transacao")
    @Operation(summary = "Delete all transactions", 
              description = "Removes all stored transactions from memory")
    @ApiResponse(responseCode = "200", description = "All transactions deleted successfully")
    public ResponseEntity<Void> deleteTransactions() {
        transactionService.deleteAllTransactions();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/estatistica")
    @Operation(summary = "Get transaction statistics", 
              description = "Returns statistics for transactions in the last 60 seconds")
    @ApiResponse(responseCode = "200", description = "Statistics retrieved successfully")
    public ResponseEntity<Statistics> getStatistics() {
        return ResponseEntity.ok(transactionService.getStatistics());
    }
}
