package br.com.otbank.businesslogic;

import br.com.otbank.cliente.Client;
import br.com.otbank.transacao.TransactionDTO;
import br.com.otbank.transacao.TransactionType;

import java.math.BigDecimal;

public class Credito extends OperationAbstract{

    public Credito(OperationAbstract operation) {
        super(operation);
    }

    @Override
    public OperationAbstract calculate(TransactionDTO transactionDTO, Client client) {
        if(transactionDTO.getType() == TransactionType.CREDIT){
            BigDecimal initialValue = client.getAccount().getBalance();
            client.getAccount().setBalance(initialValue.add(transactionDTO.getValue()));
        }
        return this;
    }
}
