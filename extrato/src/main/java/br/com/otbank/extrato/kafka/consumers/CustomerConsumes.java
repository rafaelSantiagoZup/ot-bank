package br.com.otbank.extrato.kafka.consumers;

import br.com.otbank.extrato.models.Customer;
import br.com.otbank.extrato.repository.CustomerRepository;

import java.util.Optional;

public class CustomerConsumes {
    private String id;
    private String name;
    private String email;

    @Deprecated
    public CustomerConsumes() {
    }

    public CustomerConsumes(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Customer toModel(CustomerRepository customerRepository){

        Optional<Customer> customer = customerRepository.findById(id);

        if(customer.isPresent()){
            return customer.get();
        }

        return new Customer(this.id, this.name, this.email);
    }
}