package br.com.otbank.extrato.repository;

import br.com.otbank.extrato.models.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
    Page<Transaction> findByAccountId(String id, Pageable pageable);
}