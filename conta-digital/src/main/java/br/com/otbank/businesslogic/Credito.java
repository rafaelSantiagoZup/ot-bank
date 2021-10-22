package br.com.otbank.businesslogic;

import br.com.otbank.cliente.Customer;
import br.com.otbank.transacao.TransactionDTO;
import br.com.otbank.transacao.TransactionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class Credito extends OperationAbstract{

    private Logger logger = LoggerFactory.getLogger(Credito.class);

    public Credito(OperationAbstract operation) {
        super(operation);
    }

    @Override
    public OperationAbstract calculate(TransactionDTO transactionDTO, Customer client) {
        if(transactionDTO.getType() == TransactionType.CREDIT){
            BigDecimal initialValue = client.getAccount().getBalance();
            logger.info(initialValue.toString());
            client.getAccount().setBalance(initialValue.add(transactionDTO.getValue()));
            logger.info(client.getAccount().getBalance().toString());
        }
        return next.calculate(transactionDTO,client);
    }
}
