package br.com.otbank.extrato.response;

import br.com.otbank.extrato.models.BankTransactional;
import br.com.otbank.extrato.models.TransactionType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionalResponse {

    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    private LocalDateTime occurredOn;

    public TransactionalResponse(BankTransactional bankTransactional) {
        this.value = bankTransactional.getValue();
        this.transactionType = bankTransactional.getTransactionType();
        this.occurredOn = bankTransactional.getOccurredOn();
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
}