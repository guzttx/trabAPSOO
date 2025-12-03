package factory;

import model.Produto;

/**
 * Creator abstrato do padrão Factory Method.
 * Define o método fábrica que será implementado pelas subclasses concretas.
 */
public abstract class GerenciadorCriacao {
    
    public abstract Produto criarProduto(String nome, String codigo, int quantidade);
}
