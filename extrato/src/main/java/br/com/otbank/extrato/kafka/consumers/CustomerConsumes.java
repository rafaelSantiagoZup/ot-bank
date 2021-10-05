package br.com.otbank.extrato.kafka.consumers;

public class CustomerConsumes {
    private String nome;
    private String email;

    @Deprecated
    public CustomerConsumes() {}

    public CustomerConsumes(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
}