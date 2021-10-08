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
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class AccountController {

    @Autowired
    private final ClientRepository clientRepository;

    public AccountController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @PostMapping("/accounts")
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
    @GetMapping("/clients")
    ResponseEntity<?> getAllClients(){
        List<Client> clients = clientRepository.findAll();
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/accounts/{id}")
    ResponseEntity<?> getBalance(@PathVariable UUID id){
        Optional<Client> client = clientRepository.findById(id);
        if(client.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        ClientResponse response = new ClientResponse(client.get());

        return ResponseEntity.ok(response);
    }
}
