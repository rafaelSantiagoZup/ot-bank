package br.com.otbank.contadigital;

import br.com.otbank.transacao.TransactionDTO;
import br.com.otbank.transacao.TransactionType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class NovaTransacao {

    @Autowired
    private MockMvc mvc;

  @Test
    void deveCadastrarNovaTransacao() throws Exception {
//
//        TransactionDTO body = new TransactionDTO("9c4bcaf5-1d9b-4d58-a76b-c599b222acdc", TransactionType.CREDIT, new BigDecimal("200.0"));
//        MockHttpServletRequestBuilder request = post("/api/v1/accounts")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(new ObjectMapper().writeValueAsString(body));
//
//        mvc.perform(request)
//                .andExpect(status().isOk());
//
    }

}
