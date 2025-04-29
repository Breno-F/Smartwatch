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
@Entity(name = "tb_produtos")
public class ProdutoModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(name = "nome_produto")
    private String nome;

    @Column(name = "modelo_produto", unique = true)
    private String modelo;
    
    @Column(name = "marca_produto")
    private String marca;

    @Column(name = "fabricante_produto")
    private String fabricante;

    @Column(name = "valor_produto")
    private String valor;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public ProdutoModel() {}

    public ProdutoModel(String nome, String modelo, String marca, String fabricante, String valor) {
        this.nome = nome;
        this.modelo = modelo;
        this.marca = marca;
        this.fabricante = fabricante;
        this.valor = valor;

    }
}    