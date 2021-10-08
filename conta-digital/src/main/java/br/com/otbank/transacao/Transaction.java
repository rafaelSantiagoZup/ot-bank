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
    private Client customer;
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    public Transaction(BigDecimal value, LocalDateTime occurredIn, Client client, TransactionType type) {
        this.value = value;
        this.occurredIn = occurredIn;
        this.customer = client;
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

    public TransactionType getType() {
        return type;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public void setOccurredIn(LocalDateTime occurredIn) {
        this.occurredIn = occurredIn;
    }

    public Client getCustomer() {
        return customer;
    }

    public void setCustomer(Client customer) {
        this.customer = customer;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }
}
