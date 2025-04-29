package com.example.AulaTeste.model;


import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity(name = "tb_pedidos")
public class PedidoModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(name = "nome_user")
    private String nome;
    
    @Column(name = "email_user", unique = true)
    private String email;

    @Column(name = "senha_user")
    private String senha;

    @Column(name = "endereco_user")
    private String endereco;

    @Column(name = "telefone_user")
    private String telefone;

    @Column(name = "cpf_user")
    private String cpf;

    @Column(name = "datanasci_user")
    private String data_nasci;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public PedidoModel() {}

    public PedidoModel(String nome, String email, String senha, String endereco, String telefone, String cpf, String data_nasci) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
        this.telefone = telefone;
        this.cpf = cpf;
        this.data_nasci = data_nasci;

    }
}    