package br.com.otbank.extrato.controller;

import br.com.otbank.extrato.models.BankTransactional;
import br.com.otbank.extrato.repository.TransactionalRepository;
import br.com.otbank.extrato.response.TransactionalResponse;
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

import javax.websocket.server.PathParam;
import java.util.Optional;

@RestController
@RequestMapping("/extracts/transactions")
public class TransactionalController {

    @Autowired
    private TransactionalRepository transactionalRepository;

    /**
     * As pessoas devem conseguir visualizar no aplicativo as operações que aconteceram na sua conta.
     * Portanto, precisamos listar sempre as 20 últimas transações que ocorreram para determinada pessoa.
     */

    @GetMapping(value = "/{id}")
    public ResponseEntity consultTransactions(@PathVariable String id,
                                              @PageableDefault(sort = "occurredOn", direction = Sort.Direction.DESC,
                                                      size = 20) Pageable pageable) {

        Page<BankTransactional> bankTransactional = transactionalRepository.findByAccountId(id, pageable);

        return ResponseEntity.ok(bankTransactional.map(TransactionalResponse::new));
    }
}