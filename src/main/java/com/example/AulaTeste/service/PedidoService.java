package com.example.AulaTeste.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AulaTeste.errors.PedidoJaExiste;
import com.example.AulaTeste.model.PedidoModel;
import com.example.AulaTeste.repository.IPedidoRepository;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.transaction.Transactional;
@Service
public class PedidoService {
    @Autowired
    private IPedidoRepository pedidoRepository;

    public PedidoModel criarPedido(PedidoModel pedidoModel) {
        var pedidoExistente = pedidoRepository.findByEmail(pedidoModel.getEmail());
        if (pedidoExistente != null) {
            throw new PedidoJaExiste();
        }

        String senhaCriptografada = BCrypt.withDefaults().hashToString(12, pedidoModel.getSenha().toCharArray());
        pedidoModel.setSenha(senhaCriptografada);

        return pedidoRepository.save(pedidoModel);
    }

    public List<PedidoModel> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public PedidoModel buscarPorEmail(String email) {
        return pedidoRepository.findByEmail(email);
    }

    public boolean autenticar(String email, String senha) {
        PedidoModel pedido = pedidoRepository.findByEmail(email);
        if (pedido == null) return false;

        return BCrypt.verifyer().verify(senha.toCharArray(), pedido.getSenha()).verified;
    }

    @Transactional
    public void deletarPorEmail(String email) {
        pedidoRepository.deleteByEmail(email);
    }
}
