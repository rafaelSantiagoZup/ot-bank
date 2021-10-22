package br.com.otbank.extrato.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
public class Account {
    @NotNull
    private Integer agency;
    @NotNull
    private String accountNumber;
    @Positive
    private BigDecimal balance;

    @Deprecated
    public Account() {
    }

    public Account(Integer agency, String accountNumber, BigDecimal balance) {
        this.agency = agency;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }


    public Integer getAgency() {
        return agency;
    }

    public void setAgency(Integer agency) {
        this.agency = agency;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return Objects.equals(agency, account.agency) && Objects.equals(accountNumber, account.accountNumber) && Objects.equals(balance, account.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(agency, accountNumber, balance);
    }
}