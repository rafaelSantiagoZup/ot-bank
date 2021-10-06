package br.com.otbank.extrato.kafka.consumers;

import br.com.otbank.extrato.models.BankTransactional;
import br.com.otbank.extrato.models.TransactionType;
import br.com.otbank.extrato.repository.AccountRepository;
import br.com.otbank.extrato.repository.CustomerRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionConsumes {

    private String id;

    private BigDecimal value;
    private TransactionType transactionType;
    private LocalDateTime occurredIn = LocalDateTime.now();
    private AccountConsumes account;

    @Deprecated
    public TransactionConsumes() {

    }

    public TransactionConsumes(String id, BigDecimal value, TransactionType transactionType, AccountConsumes account) {
        this.id = id;
        this.value = value;
        this.transactionType = transactionType;
        this.account = account;
    }

    public BankTransactional toModel(AccountRepository accountRepository, CustomerRepository customerRepository) {
        return new BankTransactional(this.id, this.value, this.transactionType, this.account.toModel(accountRepository, customerRepository));
    }
}