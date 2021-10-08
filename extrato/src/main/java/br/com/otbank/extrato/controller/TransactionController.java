package br.com.otbank.extrato.controller;

import br.com.otbank.extrato.models.Transaction;
import br.com.otbank.extrato.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/{id}/transacoes")
    public ResponseEntity<?> consultTransactions
            (@PathVariable String id, @PageableDefault(sort = "AccountId", direction = Sort.Direction.DESC, size = 20) Pageable pageable) {

        Page<Transaction> transactions = transactionRepository.findByAccountId(id, pageable);
        return ResponseEntity.ok(transactions);
    }
}