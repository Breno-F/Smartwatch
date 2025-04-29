package com.example.AulaTeste.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.AulaTeste.errors.PedidoJaExiste;
import com.example.AulaTeste.model.PedidoModel;
import com.example.AulaTeste.service.PedidoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/criar")
    public ResponseEntity<?> criarPedido(@RequestBody PedidoModel pedidoModel) {
        try {
            var pedido = pedidoService.criarPedido(pedidoModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
        } catch (PedidoJaExiste e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<List<PedidoModel>> getAllPedidos() {
        var pedidos = pedidoService.listarPedidos();
        if (pedidos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/buscar")
    public ResponseEntity<PedidoModel> getPedido(@RequestParam String email) {
        var pedido = pedidoService.buscarPorEmail(email);
        if (pedido == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(pedido);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody PedidoModel pedidoModel) {
        boolean autenticado = pedidoService.autenticar(pedidoModel.getEmail(), pedidoModel.getSenha());
        if (autenticado) {
            return ResponseEntity.ok("Autenticação bem-sucedida");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha incorreta");
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<Void> deletUser(@RequestParam String email) {
        pedidoService.deletarPorEmail(email);
        return ResponseEntity.ok().build();
    }
}
