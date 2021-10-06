package br.com.otbank.extrato.controller;

import br.com.otbank.extrato.models.BankTransactional;
import br.com.otbank.extrato.repository.TransactionalRepository;
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
@RequestMapping("/transactions")
public class TransactionalController {

    @Autowired
    private TransactionalRepository transactionalRepository;

    /**
     * As pessoas devem conseguir visualizar no aplicativo as operações que aconteceram na sua conta.
     * Portanto, precisamos listar sempre as 20 últimas transações que ocorreram para determinada pessoa.
     */

    @GetMapping("/{id}")
    public ResponseEntity consultTransactions(@PathVariable Long id, @PageableDefault(sort = "AccountId", direction = Sort.Direction.DESC, size = 20) Pageable pageable) {

        //Page<BankTransactional> transactions = transactionalRepository.findByAccountId(id, pageable);




        return ResponseEntity.ok("transactions");
    }
}