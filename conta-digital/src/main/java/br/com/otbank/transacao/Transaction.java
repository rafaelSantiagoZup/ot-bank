package br.com.otbank.transacao;

import br.com.otbank.cliente.Customer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class TransactionDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @NotNull @Positive
    private BigDecimal value;
    @NotNull
    private LocalDateTime occurredIn;
    @Embedded
    private Customer customer;
    @Enumerated(EnumType.STRING)
    private TransactionType type;

}
