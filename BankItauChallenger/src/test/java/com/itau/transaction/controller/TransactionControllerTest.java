package com.itau.transaction.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itau.transaction.model.Transaction;
import com.itau.transaction.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void whenValidInput_thenReturns201() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setValor(100.0);
        transaction.setDataHora(OffsetDateTime.now().minusMinutes(5));

        when(transactionService.addTransaction(any(Transaction.class))).thenReturn(true);

        mockMvc.perform(post("/transacao")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transaction)))
                .andExpect(status().isCreated());
    }

    @Test
    public void whenFutureDate_thenReturns422() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setValor(100.0);
        transaction.setDataHora(OffsetDateTime.now().plusMinutes(5));

        when(transactionService.addTransaction(any(Transaction.class))).thenReturn(false);

        mockMvc.perform(post("/transacao")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transaction)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void whenNegativeValue_thenReturns422() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setValor(-100.0);
        transaction.setDataHora(OffsetDateTime.now());

        mockMvc.perform(post("/transacao")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transaction)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void whenDeleteTransactions_thenReturns200() throws Exception {
        mockMvc.perform(delete("/transacao"))
                .andExpect(status().isOk());
    }

    @Test
    public void whenGetStatistics_thenReturns200() throws Exception {
        mockMvc.perform(get("/estatistica"))
                .andExpect(status().isOk());
    }
}
