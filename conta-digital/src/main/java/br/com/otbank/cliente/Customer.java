package br.com.otbank.cliente;

import br.com.otbank.contadigital.Account;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Embeddable
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @NotBlank
    private String name;
    @NotNull @Email
    private String email;
    @ManyToOne
    @Embedded
    private Account account;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Account getAccount() {
        return account;
    }
}
