package br.com.otbank.extrato.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.StringJoiner;

@Entity
public class BankTransactional {
    @Id
    private String id;

    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    private LocalDateTime occurredOn;

    @ManyToOne(cascade = CascadeType.ALL)
    private Account account;

    @Deprecated
    public BankTransactional() {
    }

    public BankTransactional(String id, BigDecimal value, TransactionType transactionType, LocalDateTime occurredOn,
                             Account account) {
        this.id = id;
        this.value = value;
        this.transactionType = transactionType;
        this.occurredOn = occurredOn;
        this.account = account;
    }

    public BigDecimal getValue() {
        return value;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public LocalDateTime getOccurredOn() {
        return occurredOn;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BankTransactional.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("value=" + value)
                .add("transactionType=" + transactionType)
                .add("occurredOn=" + occurredOn)
                .add("account=" + account)
                .toString();
    }
}