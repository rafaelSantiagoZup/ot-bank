package br.com.otbank.cliente;

import java.math.BigDecimal;
import java.util.UUID;

public class CustomerResponse {

    private String customerId;
    private BigDecimal balance;

    public CustomerResponse(Customer client) {
        this.customerId = client.getCustomerId();
        this.balance = client.getAccount().getBalance();
    }

    public String getCustomerId() {
        return customerId;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
