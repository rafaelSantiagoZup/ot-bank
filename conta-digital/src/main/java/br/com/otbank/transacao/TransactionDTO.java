package br.com.otbank.transacao;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.UUID;

public class TransactionDTO {

    @NotBlank
    private UUID idCostumer;
    @Enumerated(EnumType.STRING)
    @NotBlank
    private TransactionType type;
    @NotNull @Positive
    private BigDecimal value;

    public TransactionDTO(@Valid Transaction transaction){
        this.idCostumer = transaction.getCustomer().getId();
        this.type = transaction.getType();
        this.value = transaction.getValue();
    }

    public TransactionType getType() {
        return type;
    }

    public BigDecimal getValue() {
        return value;
    }
}
