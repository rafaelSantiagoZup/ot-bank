package br.com.otbank.extrato.controller;

import br.com.otbank.extrato.models.TransacaoModel;
import br.com.otbank.extrato.repository.TransacaoRepository;
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
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @GetMapping("/{id}")
    public ResponseEntity consultarTransacoes
            (@PathVariable Long id, @PageableDefault(sort = "ContaId", direction = Sort.Direction.DESC, size = 20) Pageable pageable) {


        Page<TransacaoModel> transacoes = transacaoRepository.findByContaId(id, pageable);

        return ResponseEntity.ok("RETORNAR TRANSACOES");
    }
}