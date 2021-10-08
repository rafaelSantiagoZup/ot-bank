package br.com.otbank.cliente;

import br.com.otbank.contadigital.Account;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Embeddable
@Entity
public class Client {

    @Id
    private UUID id = UUID.randomUUID();
    @NotBlank
    private String name;
    @NotNull @Email
    private String email;
    @ManyToOne
    @Embedded
    private Account account;

    @Deprecated
    public Client() {
    }

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

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
