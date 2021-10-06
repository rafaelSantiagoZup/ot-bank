package br.com.otbank.extrato.repository;

import br.com.otbank.extrato.models.BankTransactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionalRepository extends JpaRepository<BankTransactional, String> {
    Page<BankTransactional> findByAccountId(String accountId, Pageable pageable);
}