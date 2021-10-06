package br.com.otbank.extrato.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class BankTransactional {

    @Id
    private String id;

    private BigDecimal value;
    private TransactionType transactionType;
    private LocalDate occurredIn;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Account account;

    @Deprecated
    public BankTransactional() {
    }

    public BankTransactional(String id, BigDecimal value, TransactionType transactionType, Account account) {
        this.id = id;
        this.value = value;
        this.transactionType = transactionType;
        this.account = account;
    }
}