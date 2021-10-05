package br.com.otbank.extrato.repository;

import br.com.otbank.extrato.models.TransacaoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<TransacaoModel, Long> {
    Page<TransacaoModel> findByContaId(Long id, Pageable pageable);
}