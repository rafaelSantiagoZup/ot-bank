package br.com.otbank.extrato.kafka.consumers;

import br.com.otbank.extrato.models.Account;
import br.com.otbank.extrato.models.Customer;
import br.com.otbank.extrato.repository.AccountRepository;
import br.com.otbank.extrato.repository.CustomerRepository;

import java.math.BigDecimal;
import java.util.Optional;

public class AccountConsumes {
    private String id;
    private String agency;
    private String number;
    private BigDecimal balance;
    private CustomerConsumes customerConsumes;

    @Deprecated
    public AccountConsumes() {
    }

    public AccountConsumes(String id, String agency, String number, BigDecimal balance,
                           CustomerConsumes customerConsumes) {
        this.id = id;
        this.agency = agency;
        this.number = number;
        this.balance = balance;
        this.customerConsumes = customerConsumes;
    }

    public Account toModel(AccountRepository accountRepository, CustomerRepository customerRepository) {

        Optional<Account> account = accountRepository.findById(this.id);

        if(account.isPresent()){
            return account.get();
        }

        return new Account(this.id, this.agency, this.number, this.balance, this.customerConsumes.toModel(customerRepository));
    }
}