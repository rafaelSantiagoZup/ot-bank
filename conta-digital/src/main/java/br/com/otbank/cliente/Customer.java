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
    private String customerId;
    @NotBlank
    private String name;
    @NotNull @Email
    private String email;
    @ManyToOne
    @Embedded
    private Account account;

    public Customer(String customerId, String name, String email, Account account) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.account = account;
    }

    @Deprecated
    public Customer() {
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
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
