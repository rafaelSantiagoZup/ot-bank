package br.com.otbank.transacao;

import br.com.otbank.cliente.Customer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {

    private UUID id = UUID.randomUUID();
    @NotNull @Positive
    private BigDecimal value;
    @NotNull
    private LocalDateTime occurredIn;
    @Embedded
    private Customer customer;
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    public UUID getId() {
        return id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public LocalDateTime getOccurredIn() {
        return occurredIn;
    }

    public Customer getCustomer() {
        return customer;
    }

    public TransactionType getType() {
        return type;
    }
}
