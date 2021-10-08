package br.com.otbank.extrato.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Transaction {
    @Id
    private String id;

    private BigDecimal value;
    private TransactionType transactionType;
    private LocalDate occurredIn;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Account account;

    @Deprecated
    public Transaction() {
    }

    public Transaction(String id, BigDecimal value, TransactionType transactionType, Account account) {
        this.id = id;
        this.value = value;
        this.transactionType = transactionType;
        this.account = account;
    }
}