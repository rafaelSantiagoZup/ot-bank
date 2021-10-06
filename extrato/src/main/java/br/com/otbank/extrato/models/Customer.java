package br.com.otbank.extrato.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.StringJoiner;

@Entity
public class Customer {
    @Id
    private String id;

    @NotBlank
    private String name;

    @Email
    private String email;

    @Deprecated
    public Customer() {
    }

    public Customer(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Customer.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("name='" + name + "'")
                .add("email='" + email + "'")
                .toString();
    }
}