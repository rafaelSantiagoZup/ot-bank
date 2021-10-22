package br.com.otbank.extrato.models;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Transaction {
    @Id
    private String id;
    @NotNull
    private BigDecimal value;
    @Enumerated(EnumType.STRING)
    @NotNull
    private TransactionType type;
    private LocalDateTime occurredIn;
    @Embedded
    private Customer customer;

    @Deprecated
    public Transaction() {
    }

    public Transaction(String id, BigDecimal value, TransactionType type, LocalDateTime occurredIn, Customer customer) {
        this.id = id;
        this.value = value;
        this.type = type;
        this.occurredIn = occurredIn;
        this.customer = customer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public LocalDateTime getOccurredIn() {
        return occurredIn;
    }

    public void setOccurredIn(LocalDateTime occurredIn) {
        this.occurredIn = occurredIn;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) && Objects.equals(value, that.value) && type == that.type && Objects.equals(occurredIn, that.occurredIn) && Objects.equals(customer, that.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, type, occurredIn, customer);
    }
}