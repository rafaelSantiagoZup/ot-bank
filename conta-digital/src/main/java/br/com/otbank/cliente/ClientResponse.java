package br.com.otbank.cliente;

import java.math.BigDecimal;
import java.util.UUID;

public class ClientResponse {

    private UUID clientId;
    private BigDecimal balance;

    public ClientResponse(Client client) {
        this.clientId = client.getId();
        this.balance = client.getAccount().getBalance();
    }

    public UUID getClientId() {
        return clientId;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
