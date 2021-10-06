package br.com.otbank.cliente;

import br.com.otbank.contadigital.Conta;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Embeddable
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @NotBlank
    private String nome;
    @NotNull @Email
    private String email;
    @ManyToOne
    @Embedded
    private Conta conta;
}
