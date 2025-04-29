package com.example.AulaTeste.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.AulaTeste.errors.ProdutoJaExiste;
import com.example.AulaTeste.model.ProdutoModel;
import com.example.AulaTeste.service.ProdutoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/criar")
    public ResponseEntity<?> criarProduto(@RequestBody ProdutoModel produtoModel) {
        try {
            var produto = produtoService.criarProduto(produtoModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(produto);
        } catch (ProdutoJaExiste e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<List<ProdutoModel>> getAllProdutos() {
        var produtos = produtoService.listarProduto();
        if (produtos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/buscar")
    public ResponseEntity<ProdutoModel> getUser(@RequestParam String modelo) {
        var produto = produtoService.buscarPorModelo(modelo);
        if (produto == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<Void> deletProduto(@RequestParam String modelo) {
        produtoService.deletarPorModelo(modelo);
        return ResponseEntity.ok().build();
    }
}
