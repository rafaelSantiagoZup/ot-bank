package br.com.otbank.extrato.repository;

import br.com.otbank.extrato.models.BankTransactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionalRepository extends JpaRepository<BankTransactional, Long> {
    Page<BankTransactional> findByAccountId(Long id, Pageable pageable);
}