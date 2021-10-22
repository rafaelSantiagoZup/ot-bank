package br.com.otbank.businesslogic;

import br.com.otbank.cliente.Customer;
import br.com.otbank.transacao.TransactionDTO;

public class NoOperation extends OperationAbstract {

    public NoOperation() {
        super(null);
    }

    @Override
    public OperationAbstract calculate(TransactionDTO transactionDTO, Customer client) {
        return null;
    }
}
