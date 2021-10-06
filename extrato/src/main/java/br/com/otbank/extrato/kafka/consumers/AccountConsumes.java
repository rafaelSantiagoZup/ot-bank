package br.com.otbank.extrato.kafka.consumers;

import br.com.otbank.extrato.models.Account;
import br.com.otbank.extrato.repository.AccountRepository;
import br.com.otbank.extrato.repository.CustomerRepository;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.StringJoiner;

public class AccountConsumes {
    private String id;

    private String agency;
    private String number;
    private BigDecimal balance;
    private CustomerConsumes customerConsumes;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public AccountConsumes(@JsonProperty("id") String id,
                           @JsonProperty("agency") String agency,
                           @JsonProperty("number") String number,
                           @JsonProperty("balance") BigDecimal balance,
                           @JsonProperty("customer") CustomerConsumes customerConsumes) {
        this.id = id;
        this.agency = agency;
        this.number = number;
        this.balance = balance;
        this.customerConsumes = customerConsumes;
    }

    public Account toModel(AccountRepository accountRepository, CustomerRepository customerRepository) {
        Optional<Account> account = accountRepository.findById(this.id);

        if (account.isPresent()) {
            return account.get();
        }

        return new Account(this.id, this.agency, this.number, this.balance, this.customerConsumes.toModel(customerRepository));
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AccountConsumes.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("agency='" + agency + "'")
                .add("number='" + number + "'")
                .add("balance=" + balance)
                .add("customerConsumes=" + customerConsumes)
                .toString();
    }
}