package br.com.otbank.extrato.kafka.consumers;

import br.com.otbank.extrato.models.BankTransactional;
import br.com.otbank.extrato.models.TransactionType;
import br.com.otbank.extrato.repository.AccountRepository;
import br.com.otbank.extrato.repository.CustomerRepository;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.StringJoiner;

public class TransactionConsumes {

    private String id;

    private BigDecimal value;
    private TransactionType transactionType;
    private LocalDateTime occurredOn;
    private AccountConsumes accountConsumes;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public TransactionConsumes(@JsonProperty("id") String id,
                               @JsonProperty("value") BigDecimal value,
                               @JsonProperty("type") TransactionType transactionType,
                               @JsonProperty("occurredIn") LocalDateTime occurredOn,
                               @JsonProperty("account") AccountConsumes accountConsumes) {
        this.id = id;
        this.value = value;
        this.transactionType = transactionType;
        this.occurredOn = occurredOn;
        this.accountConsumes = accountConsumes;
    }

    public BankTransactional toModel(AccountRepository accountRepository, CustomerRepository customerRepository) {
        return new BankTransactional(this.id, this.value, this.transactionType, this.occurredOn, this.accountConsumes.toModel(accountRepository, customerRepository));
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TransactionConsumes.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("value=" + value)
                .add("transactionType=" + transactionType)
                .add("occurredOn=" + occurredOn)
                .add("accountConsumes=" + accountConsumes)
                .toString();
    }
}