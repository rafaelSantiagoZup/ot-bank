package br.com.otbank.extrato.models;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Account {
    @Id
    private String id;

    private String agency;
    private String number;
    private BigDecimal balance;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Customer customer;

    @Deprecated
    public Account() {
    }

    public Account(String id, String agency, String number, BigDecimal balance, Customer customer) {
        this.id = id;
        this.agency = agency;
        this.number = number;
        this.balance = balance;
        this.customer = customer;
    }

    public String getAgency() {
        return agency;
    }

    public String getNumber() {
        return number;
    }
}