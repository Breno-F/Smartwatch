package com.example.AulaTeste.errors;

public class ProdutoJaExiste extends RuntimeException {
  private String mensagem;
  public ProdutoJaExiste() {
    this.mensagem = "Produto já existe";
  }

  public String getMensagem() {
      return mensagem;
  }

  public void setMensagem(String mensagem) {
      this.mensagem = mensagem;
  }
}