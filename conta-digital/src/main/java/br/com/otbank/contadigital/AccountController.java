package br.com.otbank.contadigital;

import br.com.otbank.businesslogic.Credito;
import br.com.otbank.businesslogic.Debito;
import br.com.otbank.businesslogic.NoOperation;
import br.com.otbank.businesslogic.OperationAbstract;
import br.com.otbank.cliente.Client;
import br.com.otbank.cliente.ClientRepository;
import br.com.otbank.cliente.ClientResponse;
import br.com.otbank.transacao.Transaction;
import br.com.otbank.transacao.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    @Autowired
    private final ClientRepository clientRepository;

    public AccountController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @PostMapping
    @Transactional
    ResponseEntity<?> addTransaction (@Valid @RequestBody TransactionDTO transactionDTO){
        Client client = clientRepository.getById(transactionDTO.getClientId());

        OperationAbstract calculation = new Credito(new Debito(new NoOperation()));
        calculation.calculate(transactionDTO,client);
        clientRepository.save(client);
        Transaction transaction = new Transaction(
                    client.getAccount().getBalance(),
                    LocalDateTime.now(),
                    client,
                    transactionDTO.getType()
                );
        return ResponseEntity.ok(transaction);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getBalance(@PathVariable UUID id){
        Optional<Client> client = clientRepository.findById(id);
        if(client.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        ClientResponse response = new ClientResponse(client.get());

        return ResponseEntity.ok(response);
    }
}
