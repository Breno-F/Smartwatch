package com.example.AulaTeste.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AulaTeste.errors.ProdutoJaExiste;
import com.example.AulaTeste.model.ProdutoModel;
import com.example.AulaTeste.repository.IProdutoRepository;

import jakarta.transaction.Transactional;
@Service
public class ProdutoService {
    @Autowired
    private IProdutoRepository produtoRepository;

    public ProdutoModel criarProduto(ProdutoModel produtoModel) {
        var produtoExistente = produtoRepository.findByModelo(produtoModel.getModelo());
        if (produtoExistente != null) {
            throw new ProdutoJaExiste();
        }

        return produtoRepository.save(produtoModel);
    }

    public List<ProdutoModel> listarProduto() {
        return produtoRepository.findAll();
    }

    public ProdutoModel buscarPorModelo(String modelo) {
        return produtoRepository.findByModelo(modelo);
    }

    @Transactional
    public void deletarPorModelo(String modelo) {
        produtoRepository.deleteByModelo(modelo);
    }
}
