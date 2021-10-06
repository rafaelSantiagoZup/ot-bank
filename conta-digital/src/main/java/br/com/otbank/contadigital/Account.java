package br.com.otbank.contadigital;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Embeddable
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @NotNull
    private Integer numConta;
    @Positive
    private BigDecimal saldo;
}
