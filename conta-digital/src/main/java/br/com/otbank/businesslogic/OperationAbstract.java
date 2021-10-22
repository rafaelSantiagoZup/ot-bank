package br.com.otbank.businesslogic;

import br.com.otbank.cliente.Customer;
import br.com.otbank.transacao.TransactionDTO;

public abstract class OperationAbstract {


    public OperationAbstract next;

    public OperationAbstract(OperationAbstract operation) {
        this.next = operation;
    }

    public abstract OperationAbstract calculate(TransactionDTO transactionDTO, Customer client);
}
