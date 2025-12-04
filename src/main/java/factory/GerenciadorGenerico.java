package factory;

import model.Categoria;
import model.Fornecedor;
import model.Produto;
import model.ProdutoGenerico;

public class GerenciadorGenerico extends GerenciadorCriacao {
    
    private Categoria categoria;
    private Fornecedor fornecedor;
    
    public GerenciadorGenerico(Categoria categoria, Fornecedor fornecedor) {
        this.categoria = categoria;
        this.fornecedor = fornecedor;
    }
    
    @Override
    public Produto criarProduto(String nome, String codigo, int quantidade) {
        return new ProdutoGenerico(nome, codigo, quantidade, categoria, fornecedor);
    }
}
