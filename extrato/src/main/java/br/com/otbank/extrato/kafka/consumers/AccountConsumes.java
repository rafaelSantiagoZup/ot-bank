package br.com.otbank.extrato.kafka.consumers;

import java.math.BigDecimal;

public class AccountConsumes {
    private String agency;
    private String number;
    private BigDecimal balance;

    @Deprecated
    public AccountConsumes(){}

    public AccountConsumes(String agency, String number, BigDecimal balance) {
        this.agency = agency;
        this.number = number;
        this.balance = balance;
    }

    public String getAgency() {
        return agency;
    }

    public String getNumber() {
        return number;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}