package br.com.otbank.transacao;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.UUID;

public class TransactionDTO {

    @NotNull
    private String clientId;
    @Enumerated(EnumType.STRING)
    @NotNull
    private TransactionType type;
    @NotNull @Positive
    private BigDecimal value;

    public TransactionDTO(@Valid Transaction transaction){
        this.clientId = transaction.getCustomer().getCustomerId();
        this.type = transaction.getType();
        this.value = transaction.getValue();
    }
    @Deprecated
    public TransactionDTO() {
    }

    public TransactionDTO(String s, TransactionType credit, BigDecimal bigDecimal) {
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public TransactionType getType() {
        return type;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
