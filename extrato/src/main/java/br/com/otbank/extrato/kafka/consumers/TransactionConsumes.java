package br.com.otbank.extrato.kafka.consumers;

import br.com.otbank.extrato.models.BankTransactional;
import br.com.otbank.extrato.models.KindTransaction;
import org.hibernate.cfg.NotYetImplementedException;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionConsumes {

    private String id;

    private BigDecimal value;
    private KindTransaction kindTransaction;
    private LocalDate occurredIn = LocalDate.now();
    private CustomerConsumes customer;
    private AccountConsumes accountConsumes;

    @Deprecated
    public TransactionConsumes() {

    }

    public TransactionConsumes(String id, BigDecimal value, KindTransaction kindTransaction,
                               LocalDate occurredIn, CustomerConsumes customer, AccountConsumes accountConsumes) {
        this.id = id;
        this.value = value;
        this.kindTransaction = kindTransaction;
        this.occurredIn = occurredIn;
        this.customer = customer;
        this.accountConsumes = accountConsumes;
    }

    public BankTransactional toModel() {
        throw new NotYetImplementedException("Method to be implemented after approval.");
    }
}