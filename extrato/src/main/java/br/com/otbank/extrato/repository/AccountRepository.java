package br.com.otbank.extrato.repository;

import br.com.otbank.extrato.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {

}