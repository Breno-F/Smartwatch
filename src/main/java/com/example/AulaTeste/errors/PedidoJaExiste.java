package com.example.AulaTeste.errors;

public class PedidoJaExiste extends RuntimeException {
  private String mensagem;
  public PedidoJaExiste() {
    this.mensagem = "Pedido já existe";
  }

  public String getMensagem() {
      return mensagem;
  }

  public void setMensagem(String mensagem) {
      this.mensagem = mensagem;
  }
}
