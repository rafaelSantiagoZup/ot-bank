package br.com.otbank.extrato.repository;

import br.com.otbank.extrato.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {

}