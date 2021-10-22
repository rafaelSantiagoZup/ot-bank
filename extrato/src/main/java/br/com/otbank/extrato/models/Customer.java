package br.com.otbank.extrato.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Embeddable
public class Customer {
    @NotBlank
    private String customerId;
    @NotBlank
    private String name;
    @NotNull
    @Email
    private String email;
    @Embedded
    private Account account;

    @Deprecated
    public Customer(){}


    public Customer(String customerId, String name, String email, Account account) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.account = account;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerId, customer.customerId) && Objects.equals(name, customer.name) && Objects.equals(email, customer.email) && Objects.equals(account, customer.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, name, email, account);
    }
}