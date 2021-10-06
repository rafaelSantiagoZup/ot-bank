package br.com.otbank.transacao;

import br.com.otbank.cliente.Client;

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
    private Client client;
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    public Transaction(BigDecimal value, LocalDateTime occurredIn, Client client, TransactionType type) {
        this.value = value;
        this.occurredIn = occurredIn;
        this.client = client;
        this.type = type;
    }
    @Deprecated
    public Transaction() {
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public LocalDateTime getOccurredIn() {
        return occurredIn;
    }

    public Client getCustomer() {
        return client;
    }

    public TransactionType getType() {
        return type;
    }
}
