package br.com.otbank.contadigital;

import br.com.otbank.transacao.Transaction;
import br.com.otbank.transacao.TransactionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    @PostMapping
    ResponseEntity<?> addTransaction (@Valid @RequestBody TransactionDTO transactionDTO){

        return ResponseEntity.ok().build();
    }

}
