package com.itau.transaction.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.time.OffsetDateTime;

public class Transaction {
    @NotNull(message = "Value is required")
    @PositiveOrZero(message = "Value must be positive or zero")
    private Double valor;

    @NotNull(message = "DateTime is required")
    private OffsetDateTime dataHora;

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public OffsetDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(OffsetDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
