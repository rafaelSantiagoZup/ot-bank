package br.com.otbank.contadigital;

import br.com.otbank.businesslogic.Credito;
import br.com.otbank.businesslogic.Debito;
import br.com.otbank.businesslogic.NoOperation;
import br.com.otbank.businesslogic.OperationAbstract;
import br.com.otbank.cliente.Customer;
import br.com.otbank.cliente.ClientRepository;
import br.com.otbank.cliente.CustomerResponse;
import br.com.otbank.transacao.Transaction;
import br.com.otbank.transacao.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        Customer customer = clientRepository.getById(transactionDTO.getClientId());

        OperationAbstract calculation = new Credito(new Debito(new NoOperation()));
        calculation.calculate(transactionDTO,customer);
        System.out.println(customer.getAccount().getBalance());
        clientRepository.save(customer);
        Transaction transaction = new Transaction(
                    transactionDTO.getValue(),
                LocalDateTime.now(),
                customer,
                transactionDTO.getType()
                );
        return ResponseEntity.ok(transaction);
    }
    @GetMapping("/clients")
    ResponseEntity<?> getAllClients(){
        List<Customer> clients = clientRepository.findAll();
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/accounts/{id}")
    ResponseEntity<?> getBalance(@PathVariable String id){
        Optional<Customer> client = clientRepository.findById(id);
        if(client.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        CustomerResponse response = new CustomerResponse(client.get());

        return ResponseEntity.ok(response);
    }
}
