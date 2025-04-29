package com.example.AulaTeste.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.AulaTeste.model.PedidoModel;

public interface IPedidoRepository extends JpaRepository<PedidoModel, UUID> {
  PedidoModel findByEmail(String email);
  PedidoModel deleteByEmail(String email);
}
