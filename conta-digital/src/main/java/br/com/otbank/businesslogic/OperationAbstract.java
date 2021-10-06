package br.com.otbank.businesslogic;

import br.com.otbank.cliente.Client;
import br.com.otbank.transacao.TransactionDTO;

public abstract class OperationAbstract {

    public OperationAbstract operation;

    public OperationAbstract(OperationAbstract operation) {
        this.operation = operation;
    }

    public abstract OperationAbstract calculate(TransactionDTO transactionDTO, Client client);
}
