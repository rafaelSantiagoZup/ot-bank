package br.com.otbank.contadigital;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Embeddable
public class Account {

    @Id
    private UUID id = UUID.randomUUID();
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

    public UUID getId() {
        return id;
    }
}
