package br.com.otbank.extrato.kafka.consumers;

import br.com.otbank.extrato.models.Customer;
import br.com.otbank.extrato.repository.CustomerRepository;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;
import java.util.StringJoiner;

public class CustomerConsumes {
    private String id;

    private String name;
    private String email;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CustomerConsumes(@JsonProperty("id") String id,
                            @JsonProperty("name") String name,
                            @JsonProperty("email") String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Customer toModel(CustomerRepository customerRepository) {

        Optional<Customer> customer = customerRepository.findById(id);

        if (customer.isPresent()) {
            return customer.get();
        }

        return new Customer(this.id, this.name, this.email);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CustomerConsumes.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("name='" + name + "'")
                .add("email='" + email + "'")
                .toString();
    }
}