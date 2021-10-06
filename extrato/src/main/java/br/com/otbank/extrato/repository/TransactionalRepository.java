package br.com.otbank.extrato.repository;

import br.com.otbank.extrato.models.BankTransactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionalRepository extends JpaRepository<BankTransactional, String> { }