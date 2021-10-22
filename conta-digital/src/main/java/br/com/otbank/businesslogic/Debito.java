package br.com.otbank.businesslogic;

import br.com.otbank.cliente.Customer;
import br.com.otbank.transacao.TransactionDTO;
import br.com.otbank.transacao.TransactionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class Debito extends OperationAbstract{
    private Logger logger = LoggerFactory.getLogger(Debito.class);

    public Debito(OperationAbstract operation) {
        super(operation);
    }

    @Override
    public OperationAbstract calculate(TransactionDTO transactionDTO, Customer client) {
        if(transactionDTO.getType() == TransactionType.DEBIT){
            BigDecimal initialValue = client.getAccount().getBalance();
            logger.info("inital value Debit: "+initialValue.toString());
            client.getAccount().setBalance(initialValue.subtract(transactionDTO.getValue()));
            logger.info("final value Debit: "+client.getAccount().getBalance().toString());
        }
        return next.calculate(transactionDTO,client);
    }
}
