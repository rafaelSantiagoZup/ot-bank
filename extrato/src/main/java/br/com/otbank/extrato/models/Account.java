package br.com.otbank.extrato.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.StringJoiner;

@Entity
public class Account {
    @Id
    private String id;

    private String agency;
    private String number;
    private BigDecimal balance;

    @ManyToOne(cascade = CascadeType.ALL)
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

    @Override
    public String toString() {
        return new StringJoiner(", ", Account.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("agency='" + agency + "'")
                .add("number='" + number + "'")
                .add("balance=" + balance)
                .add("customer=" + customer)
                .toString();
    }
}